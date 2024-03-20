package com.ming.site.jwt.realm;

import com.ming.site.jwt.model.JwtToken;
import com.ming.site.model.User;
import com.ming.site.service.UserNotFountRuntimeException;
import com.ming.site.service.UserService;
import com.ming.site.service.exception.TokenHasExpiredRuntimeException;
import com.ming.site.jwt.util.JwtUtil;
import io.jsonwebtoken.Claims;
import jakarta.annotation.Resource;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountRealm extends AuthorizingRealm {

    @Resource
    private JwtUtil jwtUtil;

    @Resource
    private UserService userService;

    /**
     * 多重写一个support
     * 标识这个Realm是专门用来验证JwtToken
     * 不负责验证其他的token（UsernamePasswordToken）
     */
    @Override
    public boolean supports(AuthenticationToken token) {
        return token instanceof JwtToken;
    }

    /**
     * 认证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken)
            throws AuthenticationException {
        String jwt = (String) authenticationToken.getCredentials();
        // 获取jwt中关于用户名
        String username = jwtUtil.getClaimsByToken(jwt).getSubject();
        // 查询用户
        User user = userService.findByUsernameOrMailOrMobile(username);
        if (user == null) {
            throw new UserNotFountRuntimeException();
        }


        Claims claims = jwtUtil.getClaimsByToken(jwt);
        if (jwtUtil.isTokenExpired(claims.getExpiration())) {
            throw new TokenHasExpiredRuntimeException();
        }
        return new SimpleAuthenticationInfo(user, jwt, getName());
    }

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        return null;
    }
}
