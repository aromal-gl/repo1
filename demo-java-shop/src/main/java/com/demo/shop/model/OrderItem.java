package com.demo.shop.model;

/** A single line on an order: a product and a quantity. */
public class OrderItem {
    private final Product product;
    private final int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product product() { return product; }
    public int quantity() { return quantity; }

    /** unit price * quantity. */
    public Money lineTotal() {
        return product.unitPrice().times(quantity);
    }
}
