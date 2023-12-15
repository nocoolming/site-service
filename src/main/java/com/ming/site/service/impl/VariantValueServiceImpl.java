package com.ming.site.service.impl;

import com.ming.site.mapper.VariantValueMapper;
import com.ming.site.model.VariantValue;
import com.ming.site.service.AbstractService;
import com.ming.site.service.VariantValueService;
import org.springframework.stereotype.Service;

@Service
public class VariantValueServiceImpl
        extends AbstractService<
        VariantValue,
        Long,
        VariantValueMapper>
        implements VariantValueService {
}
