package com.prgrms.restapiclone.service;

import com.prgrms.restapiclone.entity.Address;
import com.prgrms.restapiclone.entity.Order;
import com.prgrms.restapiclone.entity.OrderPart;

import java.util.List;

public interface OrderService {

    Long createOrder(String email, Address address, List<OrderPart> orderParts);

    List<Order> findOrders();

    List<Order> findOrderByEmail(String email);
}
