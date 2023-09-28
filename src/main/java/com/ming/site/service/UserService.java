package com.ming.site.service;

import com.ming.site.model.User;

public interface UserService
    extends CrudService<User, Long> {

    User findByUsernameOrMailOrMobile(String usernameOrMailOrMobile);
}
