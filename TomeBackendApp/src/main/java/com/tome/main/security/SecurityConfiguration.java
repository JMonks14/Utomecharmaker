package com.tome.main.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {
	
	@Autowired
	UserDetailsService userDetailsService;
		
    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // Set your configuration on the auth object
        auth.userDetailsService(userDetailsService);
    }

    @Bean
    public PasswordEncoder getPasswordEncoder() {
        return NoOpPasswordEncoder.getInstance();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
        	.csrf().disable()      	
        	.authorizeRequests()
//          .antMatchers("/admin").hasRole("ADMIN")
        	.antMatchers("/accounthome").hasAnyRole("ADMIN","USER")
        	.antMatchers("/viewchar").hasAnyRole("ADMIN","USER")
        	.antMatchers("/CreateChar").hasAnyRole("ADMIN","USER")
        	.antMatchers("/buyskill").hasAnyRole("ADMIN","USER")
        	.antMatchers(HttpMethod.POST, "/player/register").permitAll()
            .antMatchers("/login").permitAll()
            .antMatchers("/").permitAll()
            .and().formLogin()
            .loginPage("/login")
            .defaultSuccessUrl("/accounthome")
            .failureUrl("/loginfail")
            .and()
            .logout()
            .logoutUrl("/perform_logout")
            .invalidateHttpSession(true)
            .deleteCookies("JSESSIONID")
            .logoutSuccessUrl("/");
    }
}