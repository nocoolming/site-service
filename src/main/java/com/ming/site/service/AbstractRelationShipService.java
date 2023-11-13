package com.ming.site.service;

import com.mybatisflex.core.BaseMapper;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractRelationShipService<
        T,
        ID extends Serializable>
        implements CrudService<T, ID> {

    private static final Logger
            log = LoggerFactory.getLogger(AbstractRelationShipService.class);


    @Autowired
    protected BaseMapper<T> mapper;

    @Transactional(propagation = Propagation.REQUIRED)
    public T insert(T entity) {
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

    public boolean get(ID id) {
        T o = this.findById(id);

        return o != null;
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
    public void deleteById(ID id) {
        mapper.deleteById(id);
    }


    @Override
    public String getRepositoryString() {
        return null;
    }
}
