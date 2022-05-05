package com.prgrms.restapiclone.dto;

import com.prgrms.restapiclone.OrderStatus;
import com.prgrms.restapiclone.entity.Address;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@AllArgsConstructor
@Getter
public class CreateOrderRequest {

    private String email;

    private Address address;

    private OrderStatus orderStatus;

    private List<OrderPartRequest> orderParts;

    private LocalDateTime createdAt;
}
