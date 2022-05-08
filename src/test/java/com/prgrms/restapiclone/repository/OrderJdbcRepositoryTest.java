package com.prgrms.restapiclone.repository;

import com.prgrms.restapiclone.entity.Category;
import com.prgrms.restapiclone.entity.OrderStatus;
import com.prgrms.restapiclone.config.TestConfiguration;
import com.prgrms.restapiclone.entity.Address;
import com.prgrms.restapiclone.entity.Order;
import com.prgrms.restapiclone.entity.OrderPart;
import com.prgrms.restapiclone.entity.Part;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringJUnitConfig(TestConfiguration.class)
class OrderJdbcRepositoryTest {

    @Autowired
    NamedParameterJdbcTemplate jdbcTemplate;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    PartRepository partRepository;

    @BeforeEach
    void beforeEach() {
        clear();
    }

    private void clear() {
        jdbcTemplate.update("DELETE FROM orders_part", Collections.emptyMap());
        jdbcTemplate.update("DELETE FROM orders", Collections.emptyMap());
        jdbcTemplate.update("DELETE FROM part", Collections.emptyMap());
    }

    @Test
    @DisplayName("Order를 저장하고 조회한다.")
    void orderSaveTest() {
        // given
        Part cpu = Part.of("intel i7", Category.CPU, 50000);
        Part gpu = Part.of("Radeon", Category.GPU, 70000);

        Long cpuId = partRepository.save(cpu);
        Long gpuId = partRepository.save(gpu);

        OrderPart orderPart1 = OrderPart.of(cpuId, cpu.getPrice()*2, 2);
        OrderPart orderPart2 = OrderPart.of(gpuId, gpu.getPrice()*2, 2);
        List<OrderPart> orderParts = List.of(orderPart1, orderPart2);
        Address address = new Address("Seoul", "123123");
        String email = "aaa@gmail.com";
        OrderStatus status = OrderStatus.PAYMENT_COMPLETED;

        // when
        Order order = Order.of(email, address, status, orderParts);
        orderRepository.save(order);

        // then
        List<Order> orders = orderRepository.findAll();
        assertThat(orders.size()).isEqualTo(1);
        Order findOrder = orders.get(0);
        assertThat(findOrder.getOrderParts())
                .usingRecursiveFieldByFieldElementComparatorIgnoringFields("orderId", "orderPartId", "createdAt")
                .contains(orderPart1, orderPart2);
        assertThat(findOrder).usingRecursiveComparison()
                .ignoringFields("orderId", "orderParts", "createdAt").isEqualTo(order);
    }
}