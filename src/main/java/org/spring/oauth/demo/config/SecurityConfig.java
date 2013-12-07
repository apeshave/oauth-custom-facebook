package org.spring.oauth.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

//@EnableWebSecurity
//@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth
			.inMemoryAuthentication()
				.withUser("user")
				.password("pass")
				.roles("USER");
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
	        .authorizeRequests()
	            .antMatchers("/index.html").permitAll()
	            .antMatchers("/**").hasRole("USER")
	            .and()
	        .formLogin()
		        // .loginPage("/authentication/login") // default is /login with an HTTP get
	            // .failureUrl("/authentication/login?failed") // default is /login?error
	            // .loginProcessingUrl("/authentication/login/process");
	        	.defaultSuccessUrl("/welcome.html");
    }


}
