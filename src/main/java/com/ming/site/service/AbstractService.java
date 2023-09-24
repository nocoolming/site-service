package com.ming.site.service;

import com.ming.site.model.IdEntity;
import com.ming.site.util.SnowflakeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public abstract class AbstractService<T extends IdEntity, ID , R extends CrudRepository<T, ID>> implements CrudService<T, ID> {
    private static final Logger log = LoggerFactory.getLogger(AbstractService.class);

    @Autowired
    R repository;

    public <S extends T> S save(S entity) {
        if (!this.existsById((ID) entity.getId())) {
            entity.setId(SnowflakeUtil.nextId());
        }
//        entity.setId();
        return repository.save(entity);
    }

    public Optional<T> findById(ID id) {
        return repository.findById(id);
    }

    public boolean existsById(ID id) {
        return repository.existsById(id);
    }

    public Iterable<T> findAll() {
        return repository.findAll();
    }

    public long count() {
        return repository.count();
    }

    @Override
    public void deleteById(ID id) {
        repository.deleteById(id);
    }
}
