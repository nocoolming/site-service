package com.ming.site.service;


import com.ming.site.model.IdLongPrimaryKey;
import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractService<
        T extends IdLongPrimaryKey ,
        ID extends Serializable,
        R extends BaseMapper<T>>
        implements CrudService<T, ID> {
    private static final Logger log = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    protected R mapper;


    @Transactional(propagation = Propagation.REQUIRED)
    public T insert(T entity) {

        if (entity.getId() == null || entity.getId() <= 0) {
            entity.setId(System.currentTimeMillis());
        }

        mapper.insertSelective(entity);

        return entity;
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int update(T e) {

        return mapper.update(e);
    }

    public T findById(ID id) {
        return mapper.selectOneWithRelationsById(id);
    }

    public T get(ID id) {
        T o = this.findById(id);

        return o ;
    }

    public List<T> findAll() {

        List<T> result = mapper.selectAllWithRelations();

        return result;
    }

    @Override
    public boolean existsById(ID id) {

        return false;
    }


    public long count() {
        QueryWrapper query = QueryWrapper.create()
                .select();
        return mapper.selectCountByQuery(query);
    }

    @Transactional(propagation = Propagation.REQUIRED)
    public int deleteById(ID id) {
        return mapper.deleteById(id);
    }


    @Override
    public String getRepositoryString() {
        return null;
    }
}
