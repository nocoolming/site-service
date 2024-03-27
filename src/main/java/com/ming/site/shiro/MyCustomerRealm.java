package com.ming.site.shiro;

import com.ming.site.model.Role;
import com.ming.site.model.User;
import com.ming.site.service.UserService;
import jakarta.annotation.Resource;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.*;
import java.util.stream.Collectors;

public class MyCustomerRealm extends AuthorizingRealm {
    private static final Logger log = LoggerFactory.getLogger(MyCustomerRealm.class);

    @Resource
    UserService userService;

    // 认证过程
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        log.error("Here is doGetAuthenticationInfo");
        UsernamePasswordToken upToken = (UsernamePasswordToken) token;
        String username = upToken.getUsername();

        User user = userService.findByUsernameOrMailOrMobile(username);
        if (user == null) {
            throw new UnknownAccountException("Unknown account: " + username);
        }

        String password = user.getPassword();

        return new SimpleAuthenticationInfo(username, password, getName());
    }

    // 授权过程
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        log.error("Here is doGetAuthorizationInfo");
        String username = (String) getAvailablePrincipal(principals);

        User user = userService.findByUsernameOrMailOrMobile(username);
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();

        Set<String> roles = user.getRoles().stream().map(Role::getTitle).collect(Collectors.toSet());

        info.setRoles(roles);
        return info;
    }
}
