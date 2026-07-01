package com.demo.shop.repository;

import com.demo.shop.model.Customer;
import java.util.HashMap;
import java.util.Map;

/** In-memory {@link CustomerRepository} for the demo. */
public class InMemoryCustomerRepository implements CustomerRepository {

    private final Map<String, Customer> store = new HashMap<>();

    @Override
    public Customer findById(String id) {
        return store.get(id);
    }

    @Override
    public Customer save(Customer entity) {
        store.put(entity.id(), entity);
        return entity;
    }
}
