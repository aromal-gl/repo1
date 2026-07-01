package com.demo.shop.service;

import com.demo.shop.exception.OutOfStockException;
import com.demo.shop.model.Product;
import com.demo.shop.repository.ProductRepository;

/** Tracks and reserves stock for products. */
public class InventoryService {

    private final ProductRepository products;

    public InventoryService(ProductRepository products) {
        this.products = products;
    }

    /** Reserve {@code quantity} units of {@code sku}, or throw if insufficient. */
    public void reserve(String sku, int quantity) {
        Product product = products.findBySku(sku);
        if (product.stock() < quantity) {
            throw new OutOfStockException(sku, quantity, product.stock());
        }
        product.decrementStock(quantity);
        products.save(product);
    }
}
