package com.ming.site.api.v1;


import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.UserRole;
import com.ming.site.service.UserRoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/userRole/v1")
public class UserRoleControllerV1
    extends AbstractControllerV1<
        UserRole,
        UserRole,
        UserRoleService> {
}
