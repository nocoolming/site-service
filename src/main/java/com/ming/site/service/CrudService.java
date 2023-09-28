package com.ming.site.service;

import com.ming.site.model.IdLongPrimaryKey;

import java.util.List;

public interface CrudService<T extends IdLongPrimaryKey, I> {
    T insert(T e);

    int update(T e);

    T findById(long id);

    boolean existsById(long id);

    List<T> findAll();

    long count();

    void deleteById(long id);

    String getRepositoryString();

}
