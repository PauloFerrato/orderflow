package com.portifolio.orderflow.controller.dto;

import ch.qos.logback.core.joran.sanity.Pair;
import com.portifolio.orderflow.domain.Order;
import com.portifolio.orderflow.domain.OrderStatus;
import com.portifolio.orderflow.domain.ProductOrder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Data
public class CreateOrderRequest {

    String userId;
    List<ProductRequest> products;

    public Order createDomain() {

        var productOrders = new ArrayList<ProductOrder>();

        products.forEach(
                product -> productOrders.add(
                        ProductOrder.builder()
                                .productAmount(product.amount)
                                .productId(product.productId)
                                .build())
        );

        return Order
                .builder()
                .id(UUID.randomUUID().toString())
                .status(OrderStatus.CREATED)
                .products(productOrders)
                .userId(userId)
                .build();
    }
}
