package com.prgrms.restapiclone.entity;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Getter
public class OrderPart {

    private Long orderPartId;
    private Long partId;
    private Long orderId;
    private long price;
    private int quantity;
    private LocalDateTime createdAt;

    public static OrderPart of(Long orderPartId, Long partId, Long orderId, long price, int quantity, LocalDateTime createdAt) {
        return new OrderPart(orderPartId, partId, orderId, price, quantity, createdAt);
    }

    public static OrderPart of(Long partId, long price, int quantity) {
        return new OrderPart(null, partId, null, price, quantity, null);
    }
}
