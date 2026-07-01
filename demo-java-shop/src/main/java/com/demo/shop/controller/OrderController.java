package com.demo.shop.controller;

import com.demo.shop.exception.OrderNotFoundException;
import com.demo.shop.model.Order;
import com.demo.shop.repository.OrderRepository;
import com.demo.shop.service.OrderService;
import com.demo.shop.util.Validation;

/** Entry point for order operations (checkout, lookup). */
public class OrderController {

    private final OrderService orderService;
    private final OrderRepository orders;

    public OrderController(OrderService orderService, OrderRepository orders) {
        this.orderService = orderService;
        this.orders = orders;
    }

    /** Checkout: load the draft order and place it. Returns the priced order. */
    public Order checkout(String orderId) {
        Validation.requireNonBlank(orderId, "orderId");
        Order order = orders.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        return orderService.placeOrder(order);
    }

    public Order getOrder(String orderId) {
        Order order = orders.findById(orderId);
        if (order == null) {
            throw new OrderNotFoundException(orderId);
        }
        return order;
    }
}
