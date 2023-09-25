package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.model.Permission;
import com.ming.site.service.PermissionService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/permission")
public class PermissionControllerV1
        extends AbstractControllerV1<
        Permission, Long,
        PermissionService> {
}
