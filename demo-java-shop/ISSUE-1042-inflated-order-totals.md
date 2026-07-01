# ISSUE-1042 — Order totals are massively inflated at checkout

**Severity:** P1
**Reported by:** Support (multiple customers)
**Area:** Checkout / pricing

## Summary

Customers are seeing order totals that are far higher than expected at checkout.
A simple order for a single $100.00 item is showing a total of **$900.00**.

## Steps to reproduce

1. Create an order for one product priced at $100.00.
2. Customer is on the STANDARD tier (no discount).
3. Check out the order.

## Expected

With 8% sales tax, the total should be **$108.00** ($100.00 + $8.00 tax).

## Actual

The total is **$900.00**.

## Notes

- The amount of the overcharge scales with the configured tax rate, so it looks
  tax-related rather than discount-related.
- Discounts appear to behave correctly for SILVER/GOLD customers (the proportion
  is right), but the final number is still inflated.
- Nothing changed in the product prices.

Please find the root cause.
