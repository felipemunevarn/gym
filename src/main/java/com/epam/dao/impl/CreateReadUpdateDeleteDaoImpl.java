package com.epam.dao.impl;

import java.util.Map;

import com.epam.dao.DeleteDAO;

public class CreateReadUpdateDeleteDaoImpl<T, ID> extends CreateReadUpdateDaoImpl<T, ID> implements DeleteDAO<T, ID> {

    public CreateReadUpdateDeleteDaoImpl(Map<ID, T> storage) {
        super(storage);
    }

    @Override
    public void delete(ID id) {
        super.storage.remove(id);
    }
}
