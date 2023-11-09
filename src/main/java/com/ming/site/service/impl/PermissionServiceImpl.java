package com.ming.site.service.impl;

import com.ming.site.mapper.PermissionMapper;
import com.ming.site.model.Permission;
import com.ming.site.service.AbstractService;
import com.ming.site.service.PermissionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PermissionServiceImpl
    extends AbstractService<Permission, Long, PermissionMapper>
    implements PermissionService {
    private static final Logger log = LoggerFactory.getLogger(PermissionServiceImpl.class);
}
