package com.ming.site.api;

import com.ming.site.common.Result;
import com.ming.site.model.IdEntity;
import com.ming.site.service.AbstractService;
import com.ming.site.service.CrudService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.Optional;


public abstract class AbstractControllerV1<
        T extends IdEntity,
        ID,
        S extends CrudService<T, ID>> {
    private static final Logger log = LoggerFactory.getLogger(AbstractControllerV1.class);

    @Autowired
    protected S service;


    @PostMapping("save")
    Result<T> save(@RequestBody T param){
        T value = service.save(param);
        return Result.success(value);
    }

    @PostMapping("remove")
    Result remove(@RequestBody ID id){
        service.deleteById(id);
        return Result.success(null);
    }

    @GetMapping("{id}")
    Result<Optional<T>> get(@PathVariable ID id){
        Optional<T> value = service.findById(id);
        return Result.success(value);
    }

    @GetMapping("all")
    Result<Iterable<T>> all(){
        Iterable<T> value = service.findAll();
        return Result.success(value);
    }

}
