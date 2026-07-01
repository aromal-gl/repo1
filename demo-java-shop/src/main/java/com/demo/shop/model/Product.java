package com.demo.shop.model;

/** A sellable product. */
public class Product {
    private final String sku;
    private final String name;
    private final Category category;
    private final Money unitPrice;
    private int stock;

    public Product(String sku, String name, Category category, Money unitPrice, int stock) {
        this.sku = sku;
        this.name = name;
        this.category = category;
        this.unitPrice = unitPrice;
        this.stock = stock;
    }

    public String sku() { return sku; }
    public String name() { return name; }
    public Category category() { return category; }
    public Money unitPrice() { return unitPrice; }
    public int stock() { return stock; }

    public void decrementStock(int quantity) {
        this.stock -= quantity;
    }
}
