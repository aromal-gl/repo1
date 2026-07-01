package com.demo.shop.exception;

/** Thrown when a reservation exceeds available stock. */
public class OutOfStockException extends ShopException {
    public OutOfStockException(String sku, int requested, int available) {
        super("Out of stock for " + sku + ": requested " + requested + ", available " + available);
    }
}
