package com.epam.dao;

public interface GenericDAO<T,ID> {
    void save(T entity);
    T findByUsername(ID id);
    void update(T entity);
    void delete(ID id);
    Boolean exists(ID id);
}
