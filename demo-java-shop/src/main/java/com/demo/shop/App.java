package com.demo.shop;

import com.demo.shop.config.ShopConfig;
import com.demo.shop.controller.OrderController;
import com.demo.shop.model.Category;
import com.demo.shop.model.Customer;
import com.demo.shop.model.CustomerTier;
import com.demo.shop.model.Money;
import com.demo.shop.model.Order;
import com.demo.shop.model.OrderItem;
import com.demo.shop.model.Product;
import com.demo.shop.repository.CustomerRepository;
import com.demo.shop.repository.InMemoryCustomerRepository;
import com.demo.shop.repository.InMemoryOrderRepository;
import com.demo.shop.repository.InMemoryProductRepository;
import com.demo.shop.repository.OrderRepository;
import com.demo.shop.repository.ProductRepository;
import com.demo.shop.service.DiscountService;
import com.demo.shop.service.InventoryService;
import com.demo.shop.service.NotificationService;
import com.demo.shop.service.OrderService;
import com.demo.shop.service.PricingService;
import com.demo.shop.service.TaxCalculator;
import com.demo.shop.util.IdGenerator;

/**
 * Wires the demo app and runs one checkout.
 *
 * Expected: 1 widget @ $100, STANDARD customer, 8% tax => total $108.00.
 * Actual:   total prints as $900.00 — see ISSUE-1042. Use this to demo the
 * root_cause_analysis agent.
 */
public class App {

    public static void main(String[] args) {
        ShopConfig config = ShopConfig.defaults();

        ProductRepository productRepo = new InMemoryProductRepository();
        OrderRepository orderRepo = new InMemoryOrderRepository();
        CustomerRepository customerRepo = new InMemoryCustomerRepository();

        InventoryService inventory = new InventoryService(productRepo);
        DiscountService discounts = new DiscountService();
        TaxCalculator taxCalculator = new TaxCalculator();
        PricingService pricing = new PricingService(discounts, taxCalculator, config);
        NotificationService notifications = new NotificationService();
        OrderService orderService = new OrderService(orderRepo, inventory, pricing, notifications);
        OrderController orders = new OrderController(orderService, orderRepo);

        Product widget = new Product("SKU-1", "Widget", Category.ELECTRONICS, new Money(100.0), 50);
        productRepo.save(widget);
        Customer alice = new Customer("C-1", "Alice", "alice@example.com", CustomerTier.STANDARD);
        customerRepo.save(alice);

        Order draft = new Order(IdGenerator.next("ORD"), alice);
        draft.addItem(new OrderItem(widget, 1));
        orderRepo.save(draft);

        Order placed = orders.checkout(draft.id());
        System.out.println("Order total: " + placed.total());   // prints $900.00 (should be $108.00)
    }
}
