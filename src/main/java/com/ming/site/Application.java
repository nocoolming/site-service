package com.ming.site;

import com.ming.site.config.EncryptConfig;
import com.ming.site.util.encrypt.RSAUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;

@SpringBootApplication
@MapperScan("com.ming.site.mapper")
public class Application {
	private static final Logger log = LoggerFactory.getLogger(Application.class);
	public static void main(String[] args) {

		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http.csrf(AbstractHttpConfigurer::disable)
				.authorizeHttpRequests(authorizationManagerRequestMatcherRegistry ->
						authorizationManagerRequestMatcherRegistry.requestMatchers(HttpMethod.DELETE).hasRole("ADMIN")
								.requestMatchers("/admin/**").hasAnyRole("ADMIN")
								.requestMatchers("/user/**").hasAnyRole("USER", "ADMIN")
								.requestMatchers("/login/**").permitAll()
								.anyRequest().authenticated())
				.httpBasic(Customizer.withDefaults())
				.sessionManagement(httpSecuritySessionManagementConfigurer ->
						httpSecuritySessionManagementConfigurer.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

		return http.build();
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

}
