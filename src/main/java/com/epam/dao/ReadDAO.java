package com.epam.dao;

public interface ReadDAO<T, ID> {
    T findByUsername(ID id);
}
