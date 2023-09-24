package com.ming.site.service.impl;

import com.ming.site.model.Role;
import com.ming.site.repository.RoleRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class RoleServiceImpl
        extends AbstractService<Role, Long, RoleRepository>
        implements RoleService {
    private static final Logger log = LoggerFactory.getLogger(RoleServiceImpl.class);
}
