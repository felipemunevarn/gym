package com.epam.dao;

public interface CreateReadUpdateDeleteDAO <T, ID> extends CreateReadUpdateDAO <T, ID> {
    void deleteById(ID id);
}
