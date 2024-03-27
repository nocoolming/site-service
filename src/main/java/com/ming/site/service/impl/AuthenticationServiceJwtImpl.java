package com.ming.site.service.impl;

import com.ming.site.api.model.SignInModel;
import com.ming.site.api.model.SignOnModel;
import com.ming.site.jwt.JwtService;
import com.ming.site.model.Cart;
import com.ming.site.model.Role;
import com.ming.site.model.User;
import com.ming.site.model.UserRole;
import com.ming.site.security.UserDetailAdapter;
import com.ming.site.service.*;
import com.ming.site.util.encrypt.RSAUtil;
import jakarta.annotation.Resource;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class AuthenticationServiceJwtImpl implements AuthenticationService {
    private static final Logger log = LoggerFactory.getLogger(AuthenticationServiceJwtImpl.class);

    @Resource
    UserService userService;
    @Autowired
    protected CartService cartService;
    @Autowired
    protected UserRoleService userRoleService;
    @Autowired
    protected RoleService roleService;
    @Autowired
    protected PasswordEncoder passwordEncoder;
    @Autowired
    protected AuthenticationManager authenticationManager;

    @Autowired
    JwtService jwtService;

    @Override
    public String signIn(SignInModel model) throws UserNotFoundException, IllegalBlockSizeException, NoSuchPaddingException, BadPaddingException, NoSuchAlgorithmException, InvalidKeyException, PasswordIsWrongException {
        this.validSignIn(model);

        User user = userService.findByUsernameOrMailOrMobile(model.getUsername());

        if (user == null) {
            throw new UserNotFoundException();
        }

        String originPassword = RSAUtil.decrypt(user.getPassword());

        if (!originPassword.equals(model.getPassword())) {
            throw new PasswordIsWrongException();
        }

        UserDetailAdapter userDetailAdapter = new UserDetailAdapter(user);

        String token = jwtService.generateToken(userDetailAdapter);


        return token;
    }

    @Override
    public User signUp(SignOnModel model) throws SiteIdNullException, PasswordNullException, UserAlreadyExistsException {
        this.validSignOn(model);

        User user = new User();

        user.setUsername(model.getUsername());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        user.setMail(model.getMail());
        userService.insert(user);

        Cart cart = new Cart();
        cart.setId(user.getId());
        cart.setCreateAt(LocalDateTime.now());
        cart.setUpgradeAt(LocalDateTime.now());
        cart.setSiteId(model.getSiteId());
        cartService.insert(cart);

        List<Role> allRoles = roleService.findAll();
        Role guest = allRoles.stream().filter(role -> role.getTitle().equals("Buyers")).findFirst().get();

        UserRole userRole = new UserRole();
        userRole.setUserId(user.getId());
        userRole.setRoleId(guest.getId());
        userRoleService.insert(userRole);

        return user;
    }

    void validSignOn(SignOnModel model) throws UserAlreadyExistsException, PasswordNullException, SiteIdNullException {
        if (userService.findByUsernameOrMailOrMobile(model.getUsername()) != null) {
            throw new UserAlreadyExistsException();
        }

        if (userService.findByUsernameOrMailOrMobile(model.getMobile()) != null) {
            throw new UserAlreadyExistsException();
        }

        if (userService.findByUsernameOrMailOrMobile(model.getMail()) != null) {
            throw new UserAlreadyExistsException();
        }

        if (model.getPassword().isEmpty()) {
            throw new PasswordNullException();
        }

        if (model.getSiteId() < 0) {
            throw new SiteIdNullException();
        }
    }

    protected void validSignIn(SignInModel model) {

    }
}
