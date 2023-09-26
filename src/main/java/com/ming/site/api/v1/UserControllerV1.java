package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.User;
import com.ming.site.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/user")
public class UserControllerV1
        extends AbstractControllerV1<
        User,
        Long,
        UserService> {
    private static final Logger log = LoggerFactory.getLogger(UserControllerV1.class);

}