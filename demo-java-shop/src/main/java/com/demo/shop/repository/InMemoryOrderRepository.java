package com.demo.shop.repository;

import com.demo.shop.model.Order;
import java.util.HashMap;
import java.util.Map;

/** In-memory {@link OrderRepository} for the demo. */
public class InMemoryOrderRepository implements OrderRepository {

    private final Map<String, Order> store = new HashMap<>();

    @Override
    public Order findById(String id) {
        return store.get(id);
    }

    @Override
    public Order save(Order entity) {
        store.put(entity.id(), entity);
        return entity;
    }
}
