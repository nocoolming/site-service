package com.ming.site.service;

import com.ming.site.model.Role;

import java.util.List;

public interface RoleService extends CrudService<Role, Long> {

    List<Role> initialRoles(long userId);

    void assignRoleToUser(long userId, long roleId);

    List<Role> all(long siteId);

}
