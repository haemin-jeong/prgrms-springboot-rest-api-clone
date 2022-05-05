package com.prgrms.restapiclone.dto;

import com.prgrms.restapiclone.entity.OrderPart;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class OrderPartRequest {
    private Long partId;

    private Long price;

    private Integer quantity;

    public OrderPart toEntity() {
        return OrderPart.of(partId, price, quantity);
    }
}
