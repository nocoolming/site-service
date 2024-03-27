package com.ming.site.api.v2;


import com.ming.site.api.model.SignInModel;
import com.ming.site.common.Result;
import com.ming.site.jwt.JwtService;
import com.ming.site.service.AuthenticationService;
import com.ming.site.service.PasswordIsWrongException;
import com.ming.site.service.UserNotFoundException;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("site/v2/auth")
public class AuthenticationControllerV2 {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationControllerV2.class);

    @Resource
    AuthenticationService authenticationService;
    @Resource
    JwtService jwtService;

    @RequestMapping("signIn")
    public Result<String> signIn(@RequestBody SignInModel model)
            throws UserNotFoundException,
            IllegalBlockSizeException,
            NoSuchPaddingException,
            BadPaddingException,
            NoSuchAlgorithmException,
            InvalidKeyException,
            PasswordIsWrongException {

        String accessToken = authenticationService.signIn(model);

        return Result.ok(accessToken);
    }


}
