package com.ming.site.service.impl;

import com.ming.site.model.Variant;
import com.ming.site.model.VariantValue;
import com.ming.site.service.AbstractRelationShipService;
import com.ming.site.service.VariantService;
import com.ming.site.service.VariantValueService;
import com.mybatisflex.core.query.QueryWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class VariantValueServiceImpl
        extends AbstractRelationShipService<
        VariantValue,
        VariantValue>
        implements VariantValueService {
    private static final Logger log = LoggerFactory.getLogger(VariantValueServiceImpl.class);

    @Autowired
    VariantService variantService;

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeVariantValueByVariantIds(List<Long> variantIds) {
         variantIds.stream().forEach(
                id -> {
                    mapper.deleteByQuery(
                            QueryWrapper.create()
                                    .where("variant_id=?", id)
                    );
                }
        );

    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public void removeVariantValueByProductId(long productId) {
        List<Variant> variants = variantService.getVariantsByProductId(productId);

        List<Long> ids =
                variants.stream()
                        .map(variant -> variant.getId())
                        .toList();

        this.removeVariantValueByVariantIds(ids);
    }
}
