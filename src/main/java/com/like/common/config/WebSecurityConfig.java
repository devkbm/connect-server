package com.like.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.like.common.security.RestLoginFailureHandler;
import com.like.common.security.RestLoginSuccessHandler;
import com.like.common.security.RestLogoutSuccessHandler;
import com.like.common.security.RestAuthenticationEntryPoint;
import com.like.user.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;
	
	/*
	 * 인증되지 않은 접근에 대해 redirect(302)시키지 않고 401 Status 리턴
	 */
	@Autowired
	private RestAuthenticationEntryPoint restAuthenticationEntryPoint;
		
	/**
	 * 로그인 성공후 default target page로 redirect(302)하지 않고 Http Status(200) 리턴
	 */
	@Autowired
	private RestLoginSuccessHandler authSuccessHandler;
	
	/**
	 * 로그인 실패 시 redirect(302)하지 않고 Http Status(401) 리턴
	 */
	@Autowired
	private RestLoginFailureHandler authFailureHandler;
	
	/**
	 * 로그아웃 처리(Session 제거) 후 Success Url로 redirect(302) 시키지 않고 Http Status(200) 리턴
	 */
	@Autowired
	private RestLogoutSuccessHandler logoutSuccessHandler;	

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//http.csrf().disable()
		//    .headers().frameOptions().disable();
		
		http.csrf().disable()
			.exceptionHandling().authenticationEntryPoint(restAuthenticationEntryPoint).and()
			.authorizeRequests()
				.antMatchers("/api/**").hasRole("USER")
				.anyRequest().authenticated().and()
			// 모든 연결을 HTTPS로 강제 전환
			//.requiresChannel().anyRequest().requiresSecure().and()
			.formLogin()
				.loginProcessingUrl("/auth/login")
				.usernameParameter("username")
				.passwordParameter("password")
				.successHandler(authSuccessHandler)
				.failureHandler(authFailureHandler)
				.permitAll().and()
			.logout()
				.logoutUrl("/auth/logout")
				.logoutSuccessHandler(logoutSuccessHandler)
				.invalidateHttpSession(true)
				.deleteCookies("JSESSIONID")
				.permitAll();
			//http.portMapper().http(8080).mapsTo(8443);
		
									
		/*http.authorizeRequests()
			.antMatchers(HttpMethod.GET,"/grw/boards").permitAll()
			.antMatchers(HttpMethod.GET,"/grw/boards/articles").permitAll()
			.antMatchers(HttpMethod.GET,"/grw/boardHierarchy").permitAll()			
			.anyRequest().authenticated();*/
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
         return super.authenticationManagerBean();
	}
	
	@Bean
	public HttpSessionStrategy httpSessionStrategy() {
		return new HeaderHttpSessionStrategy();
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user").password("password").roles("USER");
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}
}
