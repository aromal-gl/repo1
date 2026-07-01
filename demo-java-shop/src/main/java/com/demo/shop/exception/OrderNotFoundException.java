package com.demo.shop.exception;

/** Thrown when an order id cannot be resolved. */
public class OrderNotFoundException extends ShopException {
    public OrderNotFoundException(String orderId) {
        super("Order not found: " + orderId);
    }
}
