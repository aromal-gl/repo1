package com.demo.shop.service;

import com.demo.shop.model.Customer;
import com.demo.shop.model.CustomerTier;
import com.demo.shop.model.Money;

/** Applies a tier-based discount to a subtotal. */
public class DiscountService {

    /** Returns the discounted subtotal for the customer's tier. */
    public Money apply(Money subtotal, Customer customer) {
        double rate = rateFor(customer.tier());
        return subtotal.times(1.0 - rate);
    }

    /** Discount rate as a fraction (0.10 == 10%). */
    private double rateFor(CustomerTier tier) {
        switch (tier) {
            case GOLD:
                return 0.15;
            case SILVER:
                return 0.10;
            default:
                return 0.0;
        }
    }
}
