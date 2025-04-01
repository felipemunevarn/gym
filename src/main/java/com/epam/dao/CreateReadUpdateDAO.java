package com.epam.dao;

public interface CreateReadUpdateDAO <T, ID> extends CreateReadDAO <T, ID> {
    void update(T entity, ID id, T newEntity);
}
