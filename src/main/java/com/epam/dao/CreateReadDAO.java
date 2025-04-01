package com.epam.dao;

import java.util.Optional;

public interface CreateReadDAO <T, ID>{
    void save(T entity);
    Optional<T> findById(ID id);
}
