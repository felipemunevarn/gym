package com.epam.dao;

public interface GenericDAO<T,ID> {
    void create(T entity);
    T read(ID id);
    void update(T entity);
    void delete(ID id);
    Boolean exists(ID id);
}
