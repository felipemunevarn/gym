package com.epam.dao.impl;

import java.util.Map;

import com.epam.dao.UpdateDAO;
import com.epam.model.Identifiable;

public class CreateReadUpdateDaoImpl<T, ID> extends CreateReadDaoImpl<T, ID> implements UpdateDAO<T, ID> {

    public CreateReadUpdateDaoImpl(Map<ID, T> storage) {
        super(storage);
    }

    @Override
    public void update(T entity) {
        ID id = ((Identifiable<ID>) entity).getId();

        if (!storage.containsKey(id)) {
            throw new IllegalArgumentException ("Entity with ID " + id + " does not exist.");
        }
    
        storage.put(id, entity);
    }
}
