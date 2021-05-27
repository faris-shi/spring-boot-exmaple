package com.github.faris.security.formlogin;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.concurrent.TimeUnit;

@EnableWebSecurity
@Configuration
@Order(2)
public class FormLoginWebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final DaoAuthenticationProvider daoAuthenticationProvider;

    public FormLoginWebSecurityConfiguration(DaoAuthenticationProvider daoAuthenticationProvider) {
        this.daoAuthenticationProvider = daoAuthenticationProvider;
    }

    @Override
    protected UserDetailsService userDetailsService() {
        return super.userDetailsService();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .authenticationProvider(daoAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String FORM_URL = "/formlogin/**";
        http
                .antMatcher(FORM_URL)
                .authorizeRequests(authorize -> authorize.
                        antMatchers("/formlogin/index").permitAll()
                        .antMatchers("/formlogin/profile/**").authenticated()
                        .antMatchers("/formlogin/admin/**").hasRole("ADMIN")
                        .antMatchers("/formlogin/management/**").hasAnyRole("ADMIN", "MANAGER")
                )

                .formLogin()
                .loginProcessingUrl("/formlogin/sign")
                .loginPage("/formlogin/login").permitAll()
                .usernameParameter("txtUsername")
                .passwordParameter("txtPassword")

                .and()
                .logout()
                .logoutUrl("/formlogin/logout")
                .logoutSuccessUrl("/formlogin/login")
                .clearAuthentication(true)
                .invalidateHttpSession(true)
                .deleteCookies("JSESSIONID", "remember-me")

                .and()
                .rememberMe()
                .tokenValiditySeconds((int) TimeUnit.DAYS.toSeconds(24))
                .key("My_Secret!!")
                .rememberMeParameter("checkRememberMe");
    }
}
