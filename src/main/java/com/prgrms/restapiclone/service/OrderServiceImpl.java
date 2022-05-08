package com.prgrms.restapiclone.service;

import com.prgrms.restapiclone.entity.OrderStatus;
import com.prgrms.restapiclone.entity.Address;
import com.prgrms.restapiclone.entity.Order;
import com.prgrms.restapiclone.entity.OrderPart;
import com.prgrms.restapiclone.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    @Override
    public Long createOrder(String email, Address address, List<OrderPart> orderParts) {
        Order newOrder = Order.of(email, address, OrderStatus.PAYMENT_COMPLETED, orderParts);
        return orderRepository.save(newOrder);
    }

    @Override
    public List<Order> findOrders() {
        return orderRepository.findAll();
    }

    @Override
    public List<Order> findOrderByEmail(String email) {
        return findOrderByEmail(email);
    }
}
