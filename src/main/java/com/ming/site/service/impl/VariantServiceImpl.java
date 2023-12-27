package com.ming.site.service.impl;

import com.ming.site.mapper.VariantMapper;
import com.ming.site.model.Variant;
import com.ming.site.service.AbstractService;
import com.ming.site.service.VariantService;
import org.springframework.stereotype.Service;

@Service
public class VariantServiceImpl
        extends AbstractService<
        Variant,
        Long,
        VariantMapper>
        implements VariantService {
}
