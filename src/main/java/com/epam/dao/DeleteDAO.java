package com.epam.dao;

public interface DeleteDAO<T, ID> {
    void delete(ID id); // Method to delete an entity by its ID
}
