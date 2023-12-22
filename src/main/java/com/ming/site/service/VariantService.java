package com.ming.site.service;

import com.ming.site.model.Variant;

import java.util.List;

public interface VariantService
        extends CrudService<Variant, Long> {

    List<Variant> getVariantsByProductId(long productId);
}
