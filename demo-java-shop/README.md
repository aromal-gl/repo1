# demo-java-shop — Java codebase for the code-ingestion + RCA demo

A small but realistic Java e-commerce service (~30 source files) for demoing Cake's
`code_structure` ingestion and the `root_cause_analysis` agent. Edit2

## Layout

```
src/main/java/com/demo/shop/
  App.java                 # wires everything, runs one checkout
  config/ShopConfig.java   # tax rate (whole-number percent), currency
  controller/              # OrderController (checkout), ProductController
  service/                 # OrderService, PricingService, DiscountService,
                           #   TaxCalculator, InventoryService, NotificationService
  repository/              # Repository<T,ID> + Product/Order/Customer repos + in-memory impls
  model/                   # Order, OrderItem, Product, Customer, Money, enums
  util/                    # Validation, IdGenerator
  exception/               # ShopException + OutOfStockException + OrderNotFoundException
ISSUE-1042-inflated-order-totals.md   # the bug ticket to feed the RCA agent
```

The call chain that matters for the demo:

```
OrderController.checkout
  -> OrderService.placeOrder
       -> PricingService.computeTotal
            -> DiscountService.apply
            -> TaxCalculator.applyTax      <-- the bug lives here
```

## Demo script

### 1. Ingest the code
Ingest the `.java` files with **ontology = `code_structure`** (UI: Assembly → Ingest file,
or push to a repo and run a GitHub/GitLab connector with `ontology=code_structure`,
`file_types=code`). Each file becomes a Module with Class/Method/Function nodes and
CALLS / IMPORTS / INHERITS_FROM edges.

### 2. (Optional) ingest the ticket
Ingest `ISSUE-1042-inflated-order-totals.md` as a normal document so the RCA agent can pull
the symptom from the knowledge base too.

### 3. Run the root_cause_analysis agent
Give it the symptom, e.g.:

> "Order totals are inflated at checkout — a single $100 item with 8% tax shows $900 instead
> of $108. Find the root cause."

**Expected finding:** `TaxCalculator.applyTax` treats the tax rate as a fraction but the rate
is a whole-number percentage (8 == 8%) — it multiplies the subtotal by the rate and never
divides by 100, so an 8% rate adds 8× the subtotal instead of 0.08×. Fix: `tax = subtotal *
(taxRatePercent / 100.0)`.

## The planted bug (for the presenter — don't show the audience first)

`service/TaxCalculator.java`, `applyTax(...)`:

```java
double tax = subtotal.amount() * taxRatePercent;          // BUG: missing / 100.0
// correct:
// double tax = subtotal.amount() * (taxRatePercent / 100.0);
```

`App.main` reproduces it: prints `Order total: $900.00` (should be `$108.00`).
