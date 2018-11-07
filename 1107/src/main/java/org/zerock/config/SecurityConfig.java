package org.zerock.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.zerock.security.CustomUserDetailsService;

import lombok.extern.log4j.Log4j;

@Configuration
@EnableWebSecurity
@Log4j
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	//비크립트
	@Bean
	public PasswordEncoder passwordEncoder() {
		
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public CustomUserDetailsService userDetailsService() {
		
		return new CustomUserDetailsService();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth.userDetailsService(userDetailsService()).passwordEncoder(passwordEncoder());
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		log.warn("--------------------");
		log.warn("security configure");
		log.warn("--------------------");
		log.warn("--------------------");
	
	http.authorizeRequests()
	.antMatchers("/sample/member").access("hasRole('ROLE_MEMBER')")
	.antMatchers("/sample/admin").access("hasRole('ROLE_ADMIN')");
	
	http.formLogin();//자동으로 로그인화면
	
	http.rememberMe().tokenValiditySeconds(604800); // 추가하면 자동으로 체크박스가 만들어짐.
	
	}

		
}
