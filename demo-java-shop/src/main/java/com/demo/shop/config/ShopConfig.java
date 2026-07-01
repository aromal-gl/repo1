package com.demo.shop.config;

/** Central configuration for the shop (tax rate, currency, etc.). */
public class ShopConfig {

    private final double taxRatePercent;
    private final String currency;

    public ShopConfig(double taxRatePercent, String currency) {
        this.taxRatePercent = taxRatePercent;
        this.currency = currency;
    }

    /** Sales tax as a whole-number percentage (e.g. 8 means 8%). */
    public double taxRatePercent() {
        return taxRatePercent;
    }

    public String currency() {
        return currency;
    }

    public static ShopConfig defaults() {
        return new ShopConfig(8.0, "USD");
    }
}
