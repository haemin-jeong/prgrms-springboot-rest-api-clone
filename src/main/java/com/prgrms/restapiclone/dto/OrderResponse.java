package com.prgrms.restapiclone.dto;

import com.prgrms.restapiclone.entity.OrderStatus;
import com.prgrms.restapiclone.entity.Address;
import com.prgrms.restapiclone.entity.Order;
import com.prgrms.restapiclone.entity.OrderPart;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class OrderResponse {

    private Long orderId;
    private String email;
    private Address address;
    private OrderStatus orderStatus;
    private List<OrderPart> parts;
    private LocalDateTime createdAt;

    public static OrderResponse from(Order order) {
        return new OrderResponse(order.getOrderId(), order.getEmail(), order.getAddress(), order.getOrderStatus(), order.getOrderParts(), order.getCreatedAt());
    }
}
