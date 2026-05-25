package com.portifolio.orderflow.service;

import com.portifolio.orderflow.domain.Order;
import com.portifolio.orderflow.integration.OrderPublisher;
import com.portifolio.orderflow.integration.OrderRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {


    private final OrderPublisher publisher;
    private final OrderRepository repository;


    public OrderService(OrderPublisher publisher, OrderRepository repository) {
        this.publisher = publisher;
        this.repository = repository;
    }

    public void createOrder(Order order) {
        repository.saveOrder(order);
        publisher.publish(order);
    }

    public Order getOrder(String orderId) {
        return repository.getOrder(orderId);
    }

    public List<Order> getOrdersByUserId(String userId) {
        return repository.getOrdersByUserId(userId);
    }
}
