package com.prgrms.restapiclone.repository;

import com.prgrms.restapiclone.entity.Order;

import java.util.List;

public interface OrderRepository {

    Long save(Order order);

    List<Order> findAll();

}
