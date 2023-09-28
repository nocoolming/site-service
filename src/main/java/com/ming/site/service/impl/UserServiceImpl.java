package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.api.model.SignOnModel;
import com.ming.site.model.User;
import com.ming.site.repository.UserRepository;
import com.ming.site.service.AbstractService;
import com.ming.site.service.UserAlreadyExistsException;
import com.ming.site.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl
        extends AbstractService<User, Long, UserRepository>
        implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public User findByUsernameOrMailOrMobile(String usernameOrMailOrMobile) {

        QueryWrapper<User> query = new QueryWrapper<>();

        query.eq("username", usernameOrMailOrMobile)
                .or()
                .eq("mail", usernameOrMailOrMobile);
//                .or()
//                .eq("mobile", usernameOrMailOrMobile);

        User user = repository.selectOne(query);
        return user;
    }

    void validSignOn(SignOnModel model) throws UserAlreadyExistsException {
        if (this.findByUsernameOrMailOrMobile(model.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }

        if (this.findByUsernameOrMailOrMobile(model.getMobile()) != null) {
            throw new UserAlreadyExistsException();
        }

        if (this.findByUsernameOrMailOrMobile(model.getEmail()) != null) {
            throw new UserAlreadyExistsException();
        }

        if(model.getPassword().isEmpty()){

        }
    }

}
