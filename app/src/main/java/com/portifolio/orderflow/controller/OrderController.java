package com.portifolio.orderflow.controller;

import com.portifolio.orderflow.controller.dto.CreateOrderRequest;
import com.portifolio.orderflow.controller.dto.OrderView;
import com.portifolio.orderflow.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @GetMapping("/{orderId}")
    public OrderView getOrder(@PathVariable String orderId) {
        return OrderView.fromOrder(service.getOrder(orderId));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public OrderView postOrder(@RequestBody CreateOrderRequest request) {
        var domain = request.createDomain();
        service.createOrder(domain);
        return OrderView.fromOrder(domain);
    }

    @GetMapping("user/{userId}")
    public List<OrderView> getOrders(@PathVariable String userId) {
         return service.getOrdersByUserId(userId).stream().map(OrderView::fromOrder).toList();
    }
}
