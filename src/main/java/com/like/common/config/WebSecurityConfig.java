package com.like.common.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.session.web.http.HeaderHttpSessionStrategy;
import org.springframework.session.web.http.HttpSessionStrategy;

import com.like.common.security.AuthFailureHandler;
import com.like.common.security.AuthSuccessHandler;
import com.like.common.security.HttpAuthenticationEntryPoint;
import com.like.common.security.HttpLogoutSuccessHandler;
import com.like.user.service.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	UserService userService;

	@Autowired
	private HttpAuthenticationEntryPoint authenticationEntryPoint;
		
	@Autowired
	private AuthSuccessHandler authSuccessHandler;
	
	@Autowired
	private AuthFailureHandler authFailureHandler;
	
	@Autowired
	private HttpLogoutSuccessHandler logoutSuccessHandler;
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userService);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		/*http.csrf().disable()
		    .headers().frameOptions().disable();*/
		
		http
		.authorizeRequests()
			.anyRequest().authenticated()
			.and()
		.formLogin()
			.loginPage("/login") 
			.permitAll(); 	
		
		//
		/*http
			.csrf().disable()
			.exceptionHandling()
				.authenticationEntryPoint(authenticationEntryPoint)
				.and()			
			.formLogin()
		 		//.permitAll()
		 		.loginProcessingUrl("/login")
		 		.usernameParameter("username")
		 		.passwordParameter("password")
		 		.successHandler(authSuccessHandler)
		 		.failureHandler(authFailureHandler)
		 		.and()
		 	.logout()
		 		//.permitAll()
		 		.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 		.logoutSuccessHandler(logoutSuccessHandler)
		 	.and()
		 .sessionManagement()
		 .maximumSessions(1);
		
		http.authorizeRequests()
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
}
