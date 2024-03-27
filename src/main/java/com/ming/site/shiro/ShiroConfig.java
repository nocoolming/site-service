package com.ming.site.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ShiroConfig {


    @Bean
    public Realm myRealm() {
        Realm realm = new MyCustomerRealm();

        return realm;
    }



    @Bean
    public ShiroFilterChainDefinition shiroFilterChainDefinition() {
        DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
        // need to accept POSTs from the login form
//        chainDefinition.addPathDefinition("/signIn", "authc");
        chainDefinition.addPathDefinition("/logout", "logout");
        return chainDefinition;
    }
}
