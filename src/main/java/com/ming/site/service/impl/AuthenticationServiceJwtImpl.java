package com.ming.site.service.impl;

import com.ming.site.api.model.SignInModel;
import com.ming.site.model.User;
import com.ming.site.service.AuthenticationService;
import com.ming.site.service.PasswordIsWrongException;
import com.ming.site.service.UserNotFoundException;
import com.ming.site.service.UserService;
import com.ming.site.util.encrypt.RSAUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
public class AuthenticationServiceJwtImpl implements AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceJwtImpl.class);

    @Resource
    UserService userService;


    @Override
    public String signIn(SignInModel model)
            throws UserNotFoundException,
            IllegalBlockSizeException,
            NoSuchPaddingException,
            BadPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            PasswordIsWrongException {
        this.validSignIn(model);

        User user = userService.findByUsernameOrMailOrMobile(model.getUsername());

        if (user == null) {
            throw new UserNotFoundException();
        }

        String originPassword = RSAUtil.decrypt(user.getPassword());

        if (!originPassword.equals(model.getPassword())) {
            throw new PasswordIsWrongException();
        }

        String accessToken = "test";


        return accessToken;
    }

    protected void validSignIn(SignInModel model) {

    }
}
