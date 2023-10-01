package com.ming.site.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ming.site.model.IdLongPrimaryKey;
import com.ming.site.util.SnowflakeUtil;
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
               if (entity.getId() <= 0 || !this.existsById(entity.getId())) {
            entity.setId(SnowflakeUtil.nextId());
        }

        repository.insert(entity);

        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int update(T e) {
        return repository.updateById(e);
    }

    public T findById(long id) {
        return repository.selectById(id);
    }

    public boolean existsById(long id) {
        QueryWrapper<T> query = new QueryWrapper<>();
        query.eq("id", id);
        return repository.exists(query);
    }

    public List<T> findAll() {
        QueryWrapper<T> query = new QueryWrapper<>();

        query.orderByAsc("id");

        List<T> result = repository.selectList(query);

        return result;
    }

    public long count() {

        QueryWrapper<T> query = new QueryWrapper<>();
        return repository.selectCount(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public void deleteById(long id) {
        repository.deleteById(id);
    }
}
