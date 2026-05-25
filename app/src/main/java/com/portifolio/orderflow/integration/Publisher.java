package com.portifolio.orderflow.integration;

import com.portifolio.orderflow.domain.Order;
import org.springframework.stereotype.Component;

@Component
public class Publisher implements OrderPublisher{
    @Override
    public void publish(Order order) {

    }
}
