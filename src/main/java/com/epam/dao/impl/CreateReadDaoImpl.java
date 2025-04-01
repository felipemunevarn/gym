package com.epam.dao.impl;

import java.util.Map;

import com.epam.dao.CreateDAO;
import com.epam.dao.GenericDAO;
import com.epam.dao.ReadDAO;
import com.epam.model.Identifiable;

public class CreateReadDaoImpl<T, ID> implements GenericDAO<T, ID>, CreateDAO<T, ID>, ReadDAO<T, ID> {
    protected final Map<ID, T> storage;

    public CreateReadDaoImpl(Map<ID, T> storage) {
        this.storage = storage;
    }
    
    @Override
    public void save(T entity) {
        ID id = ((Identifiable<ID>) entity).getId(); // Replace with actual method to get ID from entity
        storage.put(id, entity);
    }

    @Override
    public T findByUsername(ID id) {
        return storage.get(id);
    }

    @Override
    public Boolean exists(ID id) {
        return storage.containsKey(id);
    }
}
