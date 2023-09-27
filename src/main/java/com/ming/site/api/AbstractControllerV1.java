package com.ming.site.api;

import com.ming.site.common.Result;
import com.ming.site.model.IdLongPrimaryKey;
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
        T extends IdLongPrimaryKey,
        I,
        S extends CrudService<T, Long>> {
    private static final Logger log = LoggerFactory.getLogger(AbstractControllerV1.class);

    @Autowired
    protected S service;


    @PostMapping("insert")
    Result<Integer> insert(@RequestBody T o){

        log.debug(service.getRepositoryString());
        int i = service.insert(o);
        return Result.success(i);
    }

    @PostMapping("remove")
    Result remove(@RequestBody long id){
        service.deleteById(id);
        return Result.success(null);
    }

    @GetMapping("{id}")
    Result<T> get(@PathVariable long id){
        T value = service.findById(id);
        return Result.success(value);
    }

    @GetMapping("all")
    Result<Iterable<T>> all(){
        Iterable<T> value = service.findAll();
        return Result.success(value);
    }

}
