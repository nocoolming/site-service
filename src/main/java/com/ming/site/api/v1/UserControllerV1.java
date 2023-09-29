package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.api.model.SignInModel;
import com.ming.site.api.model.SignOnModel;
import com.ming.site.common.Result;
import com.ming.site.model.User;
import com.ming.site.service.*;
import com.ming.site.util.encrypt.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@RestController
@RequestMapping("site/v1/user")
public class UserControllerV1
        extends AbstractControllerV1<
        User,
        Long,
        UserService> {
    private static final Logger log = LoggerFactory.getLogger(UserControllerV1.class);

    @PostMapping("signOn")
    Result<User> signOn(@RequestBody SignOnModel model) throws UserAlreadyExistsException, SiteIdNullException, PasswordNullException {
        User user = service.signOn(model);

        return Result.ok(user);
    }

    @PostMapping("signIn")
    Result<User> signIn(@RequestBody SignInModel model) throws UserAlreadyExistsException, SiteIdNullException, PasswordNullException, UserNotFoundException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, PasswordIsWrongException {
        User user = service.signIn(model);

        return Result.ok(user);
    }


}
