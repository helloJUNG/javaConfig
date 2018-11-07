package org.zerock.config;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Bean
	public static JavaMailSender mailSender() {
		
	 JavaMailSenderImpl mailSenderImpl = new JavaMailSenderImpl();
	 
	 mailSenderImpl.setHost("smtp.naver.com");
	 mailSenderImpl.setPort(587);  
	 mailSenderImpl.setUsername("사용자 메일 계정");
	 mailSenderImpl.setPassword("비밀번호");
	 mailSenderImpl.setDefaultEncoding("UTF-8");
	  
	 Properties javaMailProps = new Properties();
	 javaMailProps.put("mail.smtp.auth", true);
	 javaMailProps.put("mail.smtp.starttls.enable", true);
	  
	 mailSenderImpl.setJavaMailProperties(javaMailProps);
	  
	 return mailSenderImpl;  
	}
	
}
