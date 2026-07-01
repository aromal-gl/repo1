package com.demo.shop.model;

/** A customer who can place orders. */
public class Customer {
    private final String id;
    private final String name;
    private final String email;
    private final CustomerTier tier;

    public Customer(String id, String name, String email, CustomerTier tier) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.tier = tier;
    }

    public String id() { return id; }
    public String name() { return name; }
    public String email() { return email; }
    public CustomerTier tier() { return tier; }
}
