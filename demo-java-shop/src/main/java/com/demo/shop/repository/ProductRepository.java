package com.demo.shop.repository;

import com.demo.shop.model.Product;

/** Persistence for products, keyed by SKU. */
public interface ProductRepository extends Repository<Product, String> {
    Product findBySku(String sku);
}
