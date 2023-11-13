package com.ming.site.api;

import com.ming.site.common.Result;
import com.ming.site.model.IdLongPrimaryKey;
import com.ming.site.model.Product;
import com.ming.site.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.io.Serializable;
import java.util.List;
import java.util.Optional;

public abstract class AbstractControllerV1<
        T extends IdLongPrimaryKey,
        // 这个是没有用到， 但是由于此版本从spring data jpa重构，所有的controller and service都有，所以先不删 了。
        ID extends Serializable,
        S extends CrudService<T, ID>> {
    private static final Logger log = LoggerFactory.getLogger(AbstractControllerV1.class);

    @Autowired
    protected S service;

    @PostMapping("insert")
    Result<T> insert(@RequestBody T o) {

        log.debug(service.getRepositoryString());
        T r = service.insert(o);
        return Result.success(r);
    }

    @PostMapping("update")
    Result<T> update(@RequestBody T o) {

        log.debug(service.getRepositoryString());
        service.update(o);
        return Result.success(o);
    }

    @PostMapping("remove")
    Result remove(@RequestBody ID id) {
        service.deleteById(id);
        return Result.success(null);
    }

    @GetMapping("{id}")
    Result<T> findById(@PathVariable ID id) {
        T value = service.findById(id);
        return Result.success(value);
    }

    @GetMapping("all")
    Result<List<T>> all() {
        List<T> value = service.findAll();
        return Result.success(value);
    }


}
