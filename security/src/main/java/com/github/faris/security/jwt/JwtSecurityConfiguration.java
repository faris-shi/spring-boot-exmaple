package com.github.faris.security.jwt;

import com.github.faris.security.configuration.UserPrincipalDetailsService;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@Order(3)
public class JwtSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    private final UserPrincipalDetailsService userPrincipalDetailsService;

    public JwtSecurityConfiguration(DaoAuthenticationProvider daoAuthenticationProvider, UserPrincipalDetailsService userPrincipalDetailsService) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
        this.userPrincipalDetailsService = userPrincipalDetailsService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests().anyRequest().authenticated()
                .and()
                .csrf().disable()
                .sessionManagement().sessionCreationPolicy(STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthenticationFilter(this.authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier(userPrincipalDetailsService), JwtUsernameAndPasswordAuthenticationFilter.class);
    }
}
