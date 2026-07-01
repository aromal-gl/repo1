package com.demo.shop.exception;

/** Base type for all domain exceptions in the shop. */
public class ShopException extends RuntimeException {
    public ShopException(String message) {
        super(message);
    }
}
