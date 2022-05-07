package com.prgrms.restapiclone.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@AllArgsConstructor
@Getter
public class CreateOrderRequest {

    @Email
    @NotEmpty
    private String email;

    @NotEmpty
    private String address;

    @NotEmpty
    private String zipcode;

    @NotNull
    private List<@Valid OrderPartRequest> orderParts;
}
