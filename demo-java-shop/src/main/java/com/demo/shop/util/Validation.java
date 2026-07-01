package com.demo.shop.util;

import com.demo.shop.exception.ShopException;

/** Small argument-validation helpers. */
public final class Validation {

    private Validation() {}

    public static void requireNonBlank(String value, String field) {
        if (value == null || value.trim().isEmpty()) {
            throw new ShopException(field + " must not be blank");
        }
    }

    public static void requirePositive(int value, String field) {
        if (value <= 0) {
            throw new ShopException(field + " must be positive, was " + value);
        }
    }
}
