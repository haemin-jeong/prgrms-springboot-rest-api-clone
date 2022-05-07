package com.prgrms.restapiclone.controller;

import com.prgrms.restapiclone.dto.CreateOrderRequest;
import com.prgrms.restapiclone.dto.OrderPartRequest;
import com.prgrms.restapiclone.dto.OrderResponse;
import com.prgrms.restapiclone.entity.Address;
import com.prgrms.restapiclone.entity.Order;
import com.prgrms.restapiclone.entity.OrderPart;
import com.prgrms.restapiclone.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping("/api/v1/orders")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class OrderController {

    private final OrderService orderService;

    @GetMapping(value = "", produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<List<OrderResponse>> findOrders() {
        List<Order> findOrders = orderService.findOrders();
        List<OrderResponse> orderResponses = convertToOrderResponsesFromOrders(findOrders);
        return ResponseEntity.ok(orderResponses);
    }

    @PostMapping(value = "", consumes = APPLICATION_JSON_VALUE, produces = APPLICATION_JSON_VALUE)
    public ResponseEntity<Long> createdOrder(@Valid @RequestBody CreateOrderRequest orderRequest) {
        List<OrderPart> orderParts = convertToOrderPartsFromOrderRequests(orderRequest);
        Address address = new Address(orderRequest.getAddress(), orderRequest.getZipcode());
        Long orderId = orderService.createOrder(orderRequest.getEmail(), address, orderParts);
        return ResponseEntity.ok(orderId);
    }

    private List<OrderPart> convertToOrderPartsFromOrderRequests(CreateOrderRequest orderRequest) {
        return orderRequest.getOrderParts().stream()
                .map(OrderPartRequest::toEntity)
                .collect(Collectors.toList());
    }

    private List<OrderResponse> convertToOrderResponsesFromOrders(List<Order> findOrders) {
        return findOrders.stream()
                .map(OrderResponse::from)
                .collect(Collectors.toList());
    }


}
