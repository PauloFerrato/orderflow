package com.portifolio.orderflow.integration.repository;

import com.portifolio.orderflow.domain.Order;
import com.portifolio.orderflow.domain.ProductOrder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbAttribute;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbBean;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbPartitionKey;
import software.amazon.awssdk.enhanced.dynamodb.mapper.annotations.DynamoDbSecondaryPartitionKey;

import java.util.List;

@Data
@Builder
@DynamoDbBean
@AllArgsConstructor
@NoArgsConstructor
public class OrderEntity {


    private String orderId;

    @DynamoDbPartitionKey
    @DynamoDbAttribute("orderId")
    public String getOrderId() {
        return orderId;
    }

    @DynamoDbSecondaryPartitionKey(indexNames = {"GSI1PK"})
    @DynamoDbAttribute("userId")
    public String getUserId() {
        return userId;
    }


    private String userId;

    private String status;

    private List<ProductOrderEntity> products;


    public static OrderEntity fromOrder(Order order){
        var products = order.getProducts().stream()
                .map(p -> new ProductOrderEntity(p.getProductId(), p.getProductAmount()))
                .toList();

        return OrderEntity.builder()
                .orderId(order.getId())
                .userId(order.getUserId())
                .status(order.getStatus().name())
                .products(products)
                .build();
    }

    public Order toOrder(){
        var productOrders = products.stream()
                .map(p -> ProductOrder.builder()
                        .productAmount(p.getQuantity())
                        .productId(p.getProductId())
                        .build())
                .toList();

        return Order.builder()
                .id(orderId)
                .userId(userId)
                .status(com.portifolio.orderflow.domain.OrderStatus.valueOf(status))
                .products(productOrders)
                .build();
    }
}
