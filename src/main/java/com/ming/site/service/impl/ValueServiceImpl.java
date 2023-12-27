package com.ming.site.service.impl;

import com.ming.site.mapper.ValueMapper;
import com.ming.site.model.Value;
import com.ming.site.service.AbstractService;
import com.ming.site.service.ValueService;
import org.springframework.stereotype.Service;

@Service
public class ValueServiceImpl
        extends AbstractService<
        Value,
        Long,
        ValueMapper>
        implements ValueService {
}
