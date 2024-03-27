package com.ming.site.service;

import com.ming.site.api.model.SignInModel;
import com.ming.site.api.model.SignOnModel;
import com.ming.site.model.User;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

public interface AuthenticationService {
    String signIn(SignInModel model)
            throws UserNotFoundException,
            IllegalBlockSizeException,
            NoSuchPaddingException,
            BadPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            PasswordIsWrongException;

    User signUp(SignOnModel model) throws SiteIdNullException, PasswordNullException, UserAlreadyExistsException;
}
