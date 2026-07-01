package com.demo.shop.repository;

import com.demo.shop.exception.ShopException;
import com.demo.shop.model.Product;
import java.util.HashMap;
import java.util.Map;

/** In-memory {@link ProductRepository} for the demo. */
public class InMemoryProductRepository implements ProductRepository {

    private final Map<String, Product> store = new HashMap<>();

    @Override
    public Product findById(String id) {
        return store.get(id);
    }

    @Override
    public Product findBySku(String sku) {
        Product product = store.get(sku);
        if (product == null) {
            throw new ShopException("Unknown product sku: " + sku);
        }
        return product;
    }

    @Override
    public Product save(Product entity) {
        store.put(entity.sku(), entity);
        return entity;
    }
}
