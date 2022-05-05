package com.prgrms.restapiclone.entity;

import com.prgrms.restapiclone.OrderStatus;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class Order {

    private Long orderId;
    private String email;
    private Address address;
    private OrderStatus orderStatus;
    private List<OrderPart> orderParts;
    private LocalDateTime createdAt;

    public static Order of(Long orderId, String email, Address address, OrderStatus orderStatus, List<OrderPart> orderParts, LocalDateTime createdAt) {
        return new Order(orderId, email, address, orderStatus, orderParts, createdAt);
    }

    public static Order of(String email, Address address, OrderStatus orderStatus, List<OrderPart> orderParts) {
        return new Order(null, email, address, orderStatus, orderParts, null);
    }
}
