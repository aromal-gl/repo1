package com.demo.shop.model;

/** A simple money value object in whole-currency units (e.g. dollars). */
public final class Money {
    private final double amount;

    public Money(double amount) {
        this.amount = amount;
    }

    public static Money zero() {
        return new Money(0.0);
    }

    public double amount() {
        return amount;
    }

    public Money plus(Money other) {
        return new Money(this.amount + other.amount);
    }

    public Money times(double factor) {
        return new Money(this.amount * factor);
    }

    @Override
    public String toString() {
        return String.format("$%.2f", amount);
    }
}
