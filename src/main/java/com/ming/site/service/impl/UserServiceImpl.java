package com.ming.site.service.impl;

import com.ming.site.model.User;
import com.ming.site.repository.UserRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        extends AbstractService<User, Long, UserRepository>
        implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);
}
