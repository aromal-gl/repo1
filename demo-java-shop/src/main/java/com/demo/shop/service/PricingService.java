package com.demo.shop.service;

import com.demo.shop.config.ShopConfig;
import com.demo.shop.model.Money;
import com.demo.shop.model.Order;
import com.demo.shop.model.OrderItem;

/** Computes the final, tax-inclusive total for an order. */
public class PricingService {

    private final DiscountService discountService;
    private final TaxCalculator taxCalculator;
    private final ShopConfig config;

    public PricingService(DiscountService discountService, TaxCalculator taxCalculator, ShopConfig config) {
        this.discountService = discountService;
        this.taxCalculator = taxCalculator;
        this.config = config;
    }

    /** Subtotal -> customer discount -> sales tax -> grand total. */
    public Money computeTotal(Order order) {
        Money subtotal = Money.zero();
        for (OrderItem item : order.items()) {
            subtotal = subtotal.plus(item.lineTotal());
        }
        Money discounted = discountService.apply(subtotal, order.customer());
        Money total = taxCalculator.applyTax(discounted, config.taxRatePercent());
        return total;
    }
}
