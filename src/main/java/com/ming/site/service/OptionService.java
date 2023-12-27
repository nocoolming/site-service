package com.ming.site.service;

import com.ming.site.model.Option;

import java.util.List;

public interface OptionService
        extends CrudService<Option, Long> {

    List<Option> getOptionsByProductId(long productId);
}
