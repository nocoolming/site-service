package com.ming.site.service.impl;

import com.ming.site.model.StockVariantValue;
import com.ming.site.service.AbstractRelationShipService;
import com.ming.site.service.StockVariantValueService;
import org.springframework.stereotype.Service;

@Service
public class StockVariantValueServiceImpl
        extends AbstractRelationShipService<
        StockVariantValue,
        StockVariantValue>
        implements StockVariantValueService {
}
