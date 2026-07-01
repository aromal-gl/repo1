package com.demo.shop.service;

import com.demo.shop.model.Money;
import com.demo.shop.model.Order;
import com.demo.shop.model.OrderItem;
import com.demo.shop.model.OrderStatus;
import com.demo.shop.repository.OrderRepository;

/** Orchestrates placing an order: stock check -> pricing -> persist -> notify. */
public class OrderService {

    private final OrderRepository orders;
    private final InventoryService inventory;
    private final PricingService pricing;
    private final NotificationService notifications;

    public OrderService(OrderRepository orders, InventoryService inventory,
                        PricingService pricing, NotificationService notifications) {
        this.orders = orders;
        this.inventory = inventory;
        this.pricing = pricing;
        this.notifications = notifications;
    }

    /** Validate stock, price the order, persist it, and notify the customer. */
    public Order placeOrder(Order order) {
        for (OrderItem item : order.items()) {
            inventory.reserve(item.product().sku(), item.quantity());
        }
        Money total = pricing.computeTotal(order);
        order.setTotal(total);
        order.setStatus(OrderStatus.PLACED);
        Order saved = orders.save(order);
        notifications.orderPlaced(saved);
        return saved;
    }
}
