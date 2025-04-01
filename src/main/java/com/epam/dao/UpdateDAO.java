package com.epam.dao;

public interface UpdateDAO<T, ID> {
    void update(T entity); // Update an entity in the storage
}
