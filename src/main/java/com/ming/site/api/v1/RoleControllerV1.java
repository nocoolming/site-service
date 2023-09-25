package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Role;
import com.ming.site.service.RoleService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/role")
public class RoleControllerV1
extends AbstractControllerV1<Role, Long, RoleService> {
}
