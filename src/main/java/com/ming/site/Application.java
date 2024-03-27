package com.ming.site;

import com.ming.site.config.EncryptConfig;
import com.ming.site.filter.DefaultFilter;
import com.ming.site.util.encrypt.RSAUtil;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
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
	public FilterRegistrationBean<DefaultFilter> DefaultFilter() {
		FilterRegistrationBean<DefaultFilter> registrationBean = new FilterRegistrationBean<>();
		registrationBean.setFilter(new DefaultFilter());
		registrationBean.addUrlPatterns("/*");
		registrationBean.setName("DefaultFilter");
		registrationBean.setOrder(1);
		return registrationBean;
	}


}
