package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.api.model.AssignUserModel;
import com.ming.site.common.Result;
import com.ming.site.model.Role;
import com.ming.site.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("site/v1/role")
public class RoleControllerV1
        extends AbstractControllerV1<Role, Long, RoleService> {
    private static final Logger log = LoggerFactory.getLogger(RoleControllerV1.class);

    @PostMapping("initialRoles")
    Result<List<Role>> initialRoles(@RequestBody long userId) {
        List<Role> roles = service.initialRoles(userId);

        return Result.ok(roles);
    }

    @PostMapping("assignUser")
    Result<String> assignUser(@RequestBody AssignUserModel assignUser) {
        service.assignRoleToUser(assignUser.getUserId(), assignUser.getRoleId());
        return Result.ok("done");
    }

    @GetMapping("all/{siteId}")
    Result<List<Role>> all(@PathVariable long siteId) {
        List<Role> value = service.all(siteId);
        return Result.success(value);
    }


}
