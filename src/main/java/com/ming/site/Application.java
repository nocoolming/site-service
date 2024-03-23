package com.ming.site;

import com.ming.site.config.EncryptConfig;
import com.ming.site.util.encrypt.RSAUtil;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.ControllerAdvice;

import java.util.TimeZone;

@Configuration
@ControllerAdvice
@SpringBootApplication
@MapperScan("com.ming.site.mapper")
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {

//		TimeZone.setDefault(TimeZone.getTimeZone("Asia/Shanghai"));
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(ApplicationContext ctx) {
		return args -> {
			this.RSAInit(ctx);;

		};
	}

	private void RSAInit(ApplicationContext ctx){
		EncryptConfig encryptConfig = (EncryptConfig) ctx.getBean("encryptConfig");

		// 配置公钥和私钥
		RSAUtil.setPrivateKey(encryptConfig.getPrivateKey());
		RSAUtil.setPublicKey(encryptConfig.getPublicKey());
	}


	@Bean
	public Realm textRealm() {
		TextConfigurationRealm realm = new TextConfigurationRealm();
		realm.setUserDefinitions("joe.coder=password,user\n" +
				"jill.coder=password,admin");

		realm.setRoleDefinitions("admin=read,write\n" +
				"user=read");
		realm.setCachingEnabled(true);
		return realm;
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDefinition = new DefaultShiroFilterChainDefinition();
		// need to accept POSTs from the login form
		chainDefinition.addPathDefinition("/login.html", "authc");
		chainDefinition.addPathDefinition("/logout", "logout");
		return chainDefinition;
	}
}
