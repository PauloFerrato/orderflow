package com.portifolio.orderflow.integration;

import com.portifolio.orderflow.domain.Order;

import java.util.List;

public interface OrderRepository {

    void saveOrder(Order order);
    Order getOrder(String orderId);
    List<Order> getOrdersByUserId(String userId);

}
