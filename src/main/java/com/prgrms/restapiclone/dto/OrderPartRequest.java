package com.prgrms.restapiclone.dto;

import com.prgrms.restapiclone.entity.OrderPart;
import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@AllArgsConstructor
@Getter
public class OrderPartRequest {

    @NotNull
    private Long partId;

    @NotNull
    @Min(0L)
    private Long price;

    @NotNull
    @Min(1)
    private Integer quantity;

    public OrderPart toEntity() {
        return OrderPart.of(partId, price, quantity);
    }
}
