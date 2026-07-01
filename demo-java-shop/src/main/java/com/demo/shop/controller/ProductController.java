package com.demo.shop.controller;

import com.demo.shop.model.Product;
import com.demo.shop.repository.ProductRepository;
import com.demo.shop.util.Validation;

/** Read access to the product catalog. */
public class ProductController {

    private final ProductRepository products;

    public ProductController(ProductRepository products) {
        this.products = products;
    }

    public Product getProduct(String sku) {
        Validation.requireNonBlank(sku, "sku");
        return products.findBySku(sku);
    }
}
