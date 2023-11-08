package com.ming.site.service;

import com.ming.site.model.Role;

import java.util.List;

public interface RoleService extends CrudService<Role, Long> {
    void assignRoleToUser(long userId, long roleId);

    List<Role> all(long siteId);

}
