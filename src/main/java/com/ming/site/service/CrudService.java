package com.ming.site.service;

import com.ming.site.model.IdLongPrimaryKey;

import java.io.Serializable;
import java.util.List;

public interface CrudService<
        T extends IdLongPrimaryKey,
        ID extends Serializable> {
    T insert(T e);

    int update(T e);

    T findById(ID id);

    boolean existsById(ID id);

    List<T> findAll();

    long count();

    void deleteById(ID id);

    String getRepositoryString();

}
