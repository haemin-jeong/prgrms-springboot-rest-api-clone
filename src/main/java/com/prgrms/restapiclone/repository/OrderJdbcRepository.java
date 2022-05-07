package com.prgrms.restapiclone.repository;

import com.prgrms.restapiclone.OrderStatus;
import com.prgrms.restapiclone.entity.Address;
import com.prgrms.restapiclone.entity.Order;
import com.prgrms.restapiclone.entity.OrderPart;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;

import java.math.BigInteger;
import java.time.LocalDateTime;
import java.util.*;

@Repository
@RequiredArgsConstructor
public class OrderJdbcRepository implements OrderRepository {

    private static final String INSERT_ORDER_PART_SQL = "INSERT INTO orders_part(part_id, order_id, price, quantity) VALUES(:partId, :orderId, :price, :quantity)";
    private static final String SELECT_ORDER_SQL = "SELECT * FROM orders";
    private static final String INSERT_ORDER_SQL = "INSERT INTO orders(email, order_status, address, zipcode) VALUES (:email, :orderStatus, :address, :zipcode)";
    private static final String SELECT_ORDER_PART_SQL = "SELECT * FROM orders_part";

    private final NamedParameterJdbcTemplate jdbcTemplate;

    @Override
    public Long save(Order order) {
        Long orderId = saveOrder(order);
        saveOrderParts(order.getOrderParts(), orderId);
        return orderId;
    }

    @Override
    public List<Order> findAll() {
        Map<Long, List<OrderPart>> orderPartMap = getOrderPartMap();
        return jdbcTemplate.query(SELECT_ORDER_SQL, getOrderRowMapper(orderPartMap));
    }

    private RowMapper<Order> getOrderRowMapper(Map<Long, List<OrderPart>> orderPartMap) {
        return (rs, rowNum) -> {
            Long orderId = rs.getLong("order_id");
            OrderStatus orderStatus = OrderStatus.valueOf(rs.getString("order_status"));
            String email = rs.getString("email");
            String address = rs.getString("address");
            String zipcode = rs.getString("zipcode");
            LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
            List<OrderPart> orderParts = orderPartMap.get(orderId);
            return Order.of(orderId, email, new Address(address, zipcode), orderStatus, orderParts, createdAt);
        };
    }

    private Map<Long, List<OrderPart>> getOrderPartMap() {
        List<OrderPart> orderParts = jdbcTemplate.query(SELECT_ORDER_PART_SQL, Collections.emptyMap(), orderPartRowMapper);

        Map<Long, List<OrderPart>> orderPartMap = new HashMap<>();

        orderParts.forEach(op -> {
            Long orderId = op.getOrderId();
            if (!orderPartMap.containsKey(orderId)) {
                orderPartMap.put(orderId, new ArrayList<>());
            }

            orderPartMap.get(orderId).add(op);
        });
        return orderPartMap;
    }

    private void saveOrderParts(List<OrderPart> orderParts, Long orderId) {
        orderParts.forEach(orderPart -> {
            Map<String, Object> paramMap = getOrderPartParamMap(orderId, orderPart);
            jdbcTemplate.update(INSERT_ORDER_PART_SQL, paramMap);
        });
    }

    private Long saveOrder(Order order) {
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        MapSqlParameterSource parameterSource = getInsertParameterSource(order);
        jdbcTemplate.update(INSERT_ORDER_SQL,
                parameterSource, keyHolder, new String[]{"order_id"});

        Long orderId = keyHolder.getKeyAs(BigInteger.class).longValue();
        return orderId;
    }

    private Map<String, Object> getOrderPartParamMap(Long orderId, OrderPart orderPart) {
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("partId", orderPart.getPartId());
        paramMap.put("orderId", orderId);
        paramMap.put("price", orderPart.getPrice());
        paramMap.put("quantity", orderPart.getQuantity());
        return paramMap;
    }

    private MapSqlParameterSource getInsertParameterSource(Order order) {
        return new MapSqlParameterSource()
                .addValue("email", order.getEmail())
                .addValue("orderStatus", order.getOrderStatus().name())
                .addValue("address", order.getAddress().getAddress())
                .addValue("zipcode", order.getAddress().getZipcode());
    }

    private final RowMapper<OrderPart> orderPartRowMapper = (rs, rowNum) -> {
        Long orderPartId = rs.getLong("order_part_id");
        Long partId = rs.getLong("part_id");
        Long orderId1 = rs.getLong("order_id");
        long price = rs.getLong("price");
        int quantity = rs.getInt("quantity");
        LocalDateTime createdAt = rs.getTimestamp("created_at").toLocalDateTime();
        return OrderPart.of(orderPartId, partId, orderId1, price, quantity, createdAt);
    };
}
