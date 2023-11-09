package com.ming.site.service;


import com.ming.site.model.IdLongPrimaryKey;
import com.ming.site.util.SnowflakeUtil;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public abstract class AbstractService<
        T extends IdLongPrimaryKey, I,
        R extends BaseMapper<T>>
        implements CrudService<T, I> {
    private static final Logger log = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    protected R repository;

    public String getRepositoryString() {
        return this.repository.toString();
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public T insert(T entity) {
        if (entity.getId() <= 0) {
            entity.setId(SnowflakeUtil.nextId());
        }

        repository.insert(entity);

        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int update(T e) {
        return repository.update(e);
    }

    public T findById(long id) {
        return repository.selectOneById(id);
    }

    public boolean get(long id) {
        T o = this.findById(id);

        return o != null;
    }

    public List<T> findAll() {
        QueryWrapper query = QueryWrapper.create()
                .select()
                .orderBy("id desc");

        List<T> result = repository.selectListByQuery(query);

        return result;
    }

    public long count() {
        QueryWrapper query = QueryWrapper.create()
                .select();
        return repository.selectCountByQuery(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
