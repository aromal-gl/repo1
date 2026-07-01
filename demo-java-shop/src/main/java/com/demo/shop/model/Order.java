package com.demo.shop.model;

import java.util.ArrayList;
import java.util.List;

/** A customer order with its line items, status, and computed total. */
public class Order {
    private final String id;
    private final Customer customer;
    private final List<OrderItem> items;
    private OrderStatus status;
    private Money total;

    public Order(String id, Customer customer) {
        this.id = id;
        this.customer = customer;
        this.items = new ArrayList<>();
        this.status = OrderStatus.DRAFT;
        this.total = Money.zero();
    }

    public String id() { return id; }
    public Customer customer() { return customer; }
    public List<OrderItem> items() { return items; }
    public OrderStatus status() { return status; }
    public Money total() { return total; }

    public void addItem(OrderItem item) { items.add(item); }
    public void setStatus(OrderStatus status) { this.status = status; }
    public void setTotal(Money total) { this.total = total; }
}
