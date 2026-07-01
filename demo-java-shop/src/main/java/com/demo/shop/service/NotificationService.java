package com.demo.shop.service;

import com.demo.shop.model.Order;

/** Sends customer-facing notifications about order lifecycle events. */
public class NotificationService {

    /** Notify the customer that their order was placed, including the total. */
    public void orderPlaced(Order order) {
        String email = order.customer().email();
        String body = "Your order " + order.id() + " was placed. Total: " + order.total();
        send(email, "Order placed", body);
    }

    private void send(String to, String subject, String body) {
        System.out.println("[email] to=" + to + " subject=" + subject + " body=" + body);
    }
}
