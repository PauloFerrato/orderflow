package com.portifolio.orderflow.controller.dto;

import lombok.Builder;
import lombok.Data;

@Data
public class ProductRequest {
    String productId;
    int amount;
}
