package com.portifolio.orderflow.domain;

import lombok.Builder;
import lombok.Getter;

@Builder
@Getter
public class ProductOrder {

    private String productId;
    private String productName;
    private int productAmount;
}
