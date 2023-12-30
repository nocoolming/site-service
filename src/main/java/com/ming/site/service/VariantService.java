package com.ming.site.service;

import com.ming.site.model.Variant;

import java.util.List;

public interface VariantService
        extends CrudService<Variant, Long> {

    void removeVariantsAndVariantValuesByProductId(long productId);

    List<Variant> getVariantsByProductId(long productId);

    List<Variant> buildVariants(long productId) throws NotAllOptionsHaveValuesException;
}
