package com.portifolio.orderflow.integration.repository;

import com.portifolio.orderflow.domain.Order;
import com.portifolio.orderflow.integration.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import software.amazon.awssdk.enhanced.dynamodb.DynamoDbTable;
import software.amazon.awssdk.enhanced.dynamodb.model.QueryConditional;

import java.util.List;

@Component
@RequiredArgsConstructor
public class Repository implements OrderRepository {

    private final DynamoDbTable<OrderEntity> table;

    @Override
    public void saveOrder(Order order) {
        table.putItem(OrderEntity.fromOrder(order));
    }

    @Override
    public Order getOrder(String orderId) {
        var toReturn = table.getItem(OrderEntity.builder().orderId(orderId).build());
        return toReturn.toOrder();
    }

    @Override
    public List<Order> getOrdersByUserId(String userId) {
        return table.index("GSI1PK").query(
                  item -> item.queryConditional(QueryConditional.keyEqualTo(k -> k.partitionValue(userId)))
                ).stream().flatMap(p -> p.items().stream())
                .map(OrderEntity::toOrder)
                .toList();
    }
}
