package com.ming.site.service.impl;


import com.ming.site.api.model.SignInModel;
import com.ming.site.api.model.SignOnModel;
import com.ming.site.model.Cart;
import com.ming.site.model.User;
import com.ming.site.repository.UserRepository;
import com.ming.site.service.*;
import com.ming.site.util.SnowflakeUtil;
import com.ming.site.util.encrypt.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;

@Service
public class UserServiceImpl
        extends AbstractService<User, Long, UserRepository>
        implements UserService {
    private static final Logger log = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    CartService cartService;
    @Override
    public User findByUsernameOrMailOrMobile(String usernameOrMailOrMobile) {
        QueryWrapper query = new QueryWrapper();
        query.eq("username", usernameOrMailOrMobile)
                .or()
                .eq("mail", usernameOrMailOrMobile);
//                .or()
//                .eq("mobile", usernameOrMailOrMobile);

        User user = repository.selectOne(query);
        return user;
    }

    @Override
    @Transactional(propagation = Propagation.REQUIRED)
    public User signOn(SignOnModel model) throws UserAlreadyExistsException, PasswordNullException, SiteIdNullException {
        this.validSignOn(model);

        String encryptedPassword = RSAUtil.encrypt(model.getPassword());

        User user = new User();
        user.setUsername(model.getUsername());
        user.setPassword(encryptedPassword);
        user.setMail(model.getMail());
        user.setId(SnowflakeUtil.nextId());
        user.setSiteId(model.getSiteId());

        repository.insert(user);

        Cart cart = new Cart();
        cart.setId(user.getId());
        cart.setCreateAt(LocalDateTime.now());
        cart.setUpgradeAt(LocalDateTime.now());
        cart.setSiteId(model.getSiteId());
        cartService.insert(cart);

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
