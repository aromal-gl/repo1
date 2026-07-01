package com.demo.shop.service;

import com.demo.shop.model.Money;

/**
 * Adds sales tax to an order subtotal.
 *
 * Tax rates are expressed as WHOLE-NUMBER PERCENTAGES — e.g. a rate of 8 means
 * 8%. See {@link com.demo.shop.config.ShopConfig#taxRatePercent()}.
 */
public class TaxCalculator {

    /**
     * Apply tax to {@code subtotal} at {@code taxRatePercent} percent and return
     * the tax-inclusive amount.
     */
    public Money applyTax(Money subtotal, double taxRatePercent) {
        // taxRatePercent is a percentage (8 == 8%), so the tax owed is
        // subtotal * (taxRatePercent / 100). The division by 100 is missing
        // here, so an 8% rate multiplies the subtotal by 8 instead of 0.08.
        double tax = subtotal.amount() * taxRatePercent;
        return subtotal.plus(new Money(tax));
    }
}
