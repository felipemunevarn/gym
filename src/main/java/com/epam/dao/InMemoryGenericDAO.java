package com.epam.dao;

import java.util.Map;

// import org.springframework.stereotype.Repository;

// @Repository
public class InMemoryGenericDAO<T, ID> implements GenericDAO<T, ID> {
    private final Map<ID, T> storage;

    public InMemoryGenericDAO(Map<ID, T> storage) {
        System.out.println(storage);
        this.storage = storage;
    }

    @Override
    public void save(T entity) {
        // Assuming the entity has a method to get its ID
        ID id = getId(entity);
        storage.put(id, entity);
    }

    @Override
    public T findByUsername(ID id) {
        return storage.get(id);
    }

    @Override
    public void update(T entity) {
        ID id = getId(entity);
        storage.put(id, entity);
    }

    @Override
    public void delete(ID id) {
        storage.remove(id);
    }

    private ID getId(T entity) {
        // Implement logic to extract ID from the entity
        return null; // Placeholder for actual implementation
    }

    @Override
    public Boolean exists(ID id) {
        // TODO Auto-generated method stub
        return storage.containsKey(id);
    }
}
