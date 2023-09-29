package com.ming.site.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.ming.site.api.model.SignInModel;
import com.ming.site.api.model.SignOnModel;
import com.ming.site.model.User;
import com.ming.site.repository.UserRepository;
import com.ming.site.service.*;
import com.ming.site.util.SnowflakeUtil;
import com.ming.site.util.encrypt.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

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

    @Override
    public User signOn(SignOnModel model) throws UserAlreadyExistsException, PasswordNullException, SiteIdNullException {
        this.validSignOn(model);

        String encryptedPassword = RSAUtil.encrypt(model.getPassword());
//        encryptedPassword = Md5

        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(encryptedPassword);
        user.setMail(model.getMail());
        user.setId(SnowflakeUtil.nextId());
        user.setSiteId(model.getSiteId());

        repository.insert(user);
        return user;
    }

    void validSignOn(SignOnModel model) throws UserAlreadyExistsException, PasswordNullException, SiteIdNullException {
        if (this.findByUsernameOrMailOrMobile(model.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }

        if (this.findByUsernameOrMailOrMobile(model.getMobile()) != null) {
            throw new UserAlreadyExistsException();
        }

        if (this.findByUsernameOrMailOrMobile(model.getMail()) != null) {
            throw new UserAlreadyExistsException();
        }

        if(model.getPassword().isEmpty()){
            throw new PasswordNullException();
        }

        if(model.getSiteId() < 0){
            throw new SiteIdNullException();
        }
    }



    @Override
    public User signIn(SignInModel model) throws UserNotFoundException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, PasswordIsWrongException {
        this.validSignIn(model);

        User user = this.findByUsernameOrMailOrMobile(model.getUsername());

        if(user == null){
            throw  new UserNotFoundException();
        }

        String originPassword = RSAUtil.decrypt(user.getPassword());

        if(!originPassword.equals(model.getPassword())){
            throw new PasswordIsWrongException();
        }

        return user;
    }

    void validSignIn(SignInModel model){

    }


}
