package com.ming.site.service;

import com.ming.site.model.Role;

public interface RoleService extends CrudService<Role, Long> {
    void assignRoleToUser(long userId, long roleId);

}
