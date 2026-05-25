package com.portifolio.orderflow.integration;

import com.portifolio.orderflow.domain.Order;

public interface OrderPublisher {
    void publish(Order order);
}
