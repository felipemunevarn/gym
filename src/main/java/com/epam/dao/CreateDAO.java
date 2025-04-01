package com.epam.dao;

public interface CreateDAO<T, ID> {
    void save(T entity); // Create a new entity in the database
}
