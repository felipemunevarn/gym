package com.epam.dao;

public interface GenericDAO<T, ID> {
    Boolean exists(ID id);
}
