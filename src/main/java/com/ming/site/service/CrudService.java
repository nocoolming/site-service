package com.ming.site.service;

import java.util.Optional;

public interface CrudService<T, ID>  {
    <S extends  T> S save(S entity);

    Optional<T> findById(ID id);

    boolean existsById(ID id);

    Iterable<T> findAll();

    long count();

    void deleteById(ID id);



}
