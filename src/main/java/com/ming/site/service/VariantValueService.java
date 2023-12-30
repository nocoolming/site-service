package com.ming.site.service;

import com.ming.site.model.VariantValue;

import java.util.List;

public interface VariantValueService
        extends CrudService<VariantValue, VariantValue> {
    void removeVariantValueByVariantIds(List<Long> variantIds);

    void removeVariantValueByProductId(long productId);
}
