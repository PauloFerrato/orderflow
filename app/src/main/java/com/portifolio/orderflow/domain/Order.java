package com.portifolio.orderflow.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Builder
public class Order {

    private String id;
    private String userId;
    private OrderStatus status;
    private List<ProductOrder> products;
}
