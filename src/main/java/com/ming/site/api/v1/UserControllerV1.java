package com.ming.site.api.v1;

import com.ming.site.api.AbstractControllerV1;
import com.ming.site.api.model.SignOnModel;
import com.ming.site.common.Result;
import com.ming.site.model.User;
import com.ming.site.service.UserAlreadyExistsException;
import com.ming.site.service.UserService;
import com.ming.site.util.encrypt.RSAUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("site/v1/user")
public class UserControllerV1
        extends AbstractControllerV1<
        User,
        Long,
        UserService> {
    private static final Logger log = LoggerFactory.getLogger(UserControllerV1.class);

    @PostMapping("signOn")
    Result<User> signOn(@RequestBody SignOnModel model) throws UserAlreadyExistsException {


        String encryptedPassword = RSAUtil.encrypt(model.getPassword());
        return null;
    }


}
