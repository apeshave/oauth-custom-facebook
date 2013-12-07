package org.spring.oauth.demo.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configurers.provisioning.InMemoryUserDetailsManagerConfigurer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.oauth2.config.annotation.authentication.configurers.InMemoryClientDetailsServiceConfigurer;
import org.springframework.security.oauth2.config.annotation.web.configuration.OAuth2ServerConfigurerAdapter;
import org.springframework.security.oauth2.config.annotation.web.configurers.OAuth2ServerConfigurer;
import org.springframework.security.oauth2.provider.token.InMemoryTokenStore;

@Configuration
@EnableWebSecurity
public class OAuthSecurityConfig extends OAuth2ServerConfigurerAdapter {
	
	private String applicationName = "custom";
	private String clientName 	= "client";
	private String clientSecret = "secret";
	
    // @formatter:off
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//    	auth.apply(new InMemoryUserDetailsManagerConfigurer<AuthenticationManagerBuilder>())
//    			.withUser("ouser")
//				.password("pass")
//				.roles("USER");

		auth.apply(new InMemoryClientDetailsServiceConfigurer())
                .withClient(clientName)
                .secret(clientSecret)
                .resourceIds(applicationName)
                .scopes("read", "write")
                .authorities("ROLE_USER")
                .authorizedGrantTypes("authorization_code", "implicit", "password")
              ;

    }
    // @formatter:on
	
	@Override
	public void configure(WebSecurity web) throws Exception {
		web.debug(true);
	}
	
	@Override
    protected void configure(HttpSecurity http) throws Exception {
		http
	        .authorizeRequests()
	            .antMatchers("/index.html", "/oauth/**").permitAll()
	            .antMatchers("/**").hasRole("USER")
	            .and()
	            .apply(new OAuth2ServerConfigurer())
                .tokenStore(new InMemoryTokenStore())
                .resourceId(applicationName)
//                .and()
//	        .formLogin()
		        // .loginPage("/authentication/login") // default is /login with an HTTP get
	            // .failureUrl("/authentication/login?failed") // default is /login?error
	            // .loginProcessingUrl("/authentication/login/process");
//	        	.defaultSuccessUrl("/welcome.html")
	        	;
    }


}
