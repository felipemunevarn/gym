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
        ID id = ((Identifiable<ID>) entity).getId(); // Replace with actual method to get ID from entity
        super.storage.put(id, entity);
    }
}
