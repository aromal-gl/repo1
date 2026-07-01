package com.demo.shop.repository;

/** Generic persistence contract. */
public interface Repository<T, ID> {
    T findById(ID id);
    T save(T entity);
}
