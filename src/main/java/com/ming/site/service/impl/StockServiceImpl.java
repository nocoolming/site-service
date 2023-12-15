package com.ming.site.service.impl;

import com.ming.site.mapper.StockMapper;
import com.ming.site.model.Stock;
import com.ming.site.service.AbstractService;
import com.ming.site.service.StockService;
import org.springframework.stereotype.Service;

@Service
public class StockServiceImpl
        extends AbstractService<
        Stock,
        Long,
        StockMapper>
        implements StockService {
}
