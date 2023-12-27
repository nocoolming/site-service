package com.ming.site.service.impl;

import com.ming.site.model.VariantValue;
import com.ming.site.service.AbstractRelationShipService;
import com.ming.site.service.VariantValueService;
import org.springframework.stereotype.Service;

@Service
public class VariantValueServiceImpl
        extends AbstractRelationShipService<
        VariantValue,
        VariantValue>
        implements VariantValueService {
}
