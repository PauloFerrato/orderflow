package com.portifolio.orderflow.controller.dto;

import com.portifolio.orderflow.domain.Order;
import com.portifolio.orderflow.integration.repository.OrderEntity;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class OrderView {
    String orderId;
    String orderStatus;

    public static OrderView fromOrder(Order order){
        return OrderView.builder()
                .orderId(order.getId())
                .orderStatus(order.getStatus().name())
                .build();
    }
}
