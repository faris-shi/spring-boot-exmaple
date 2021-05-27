package com.github.faris.security.httpbasic;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

import static com.github.faris.security.httpbasic.model.UserAuthority.*;
import static com.github.faris.security.httpbasic.model.UserRole.ADMIN;
import static com.github.faris.security.httpbasic.model.UserRole.MANAGER;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebSecurity
@Order(1)
public class HttpBasicSecurityConfiguration extends WebSecurityConfigurerAdapter {


    private final PasswordEncoder encoder;

    public HttpBasicSecurityConfiguration(PasswordEncoder passwordEncoder) {
        this.encoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        final String STUDENT_URL = "/httpbasic/students/**";
        final String COURSE_URL = "/httpbasic/courses/**";
        http
                .csrf().disable()
                .antMatcher("/httpbasic/**")
                .authorizeRequests(
                        authorize -> authorize.antMatchers("/httpbasic/info").permitAll()
                                .antMatchers("/httpbasic/profile").authenticated()

                                .antMatchers(POST, STUDENT_URL).hasAuthority(STUDENT_WRITE.name())
                                .antMatchers(DELETE, STUDENT_URL).hasAuthority(STUDENT_WRITE.name())
                                .antMatchers(PUT, STUDENT_URL).hasAuthority(STUDENT_WRITE.name())
                                .antMatchers(GET, STUDENT_URL).hasAuthority(STUDENT_READ.name())

                                .antMatchers(POST, COURSE_URL).hasAuthority(COURSE_WRITE.name())
                                .antMatchers(DELETE, COURSE_URL).hasAuthority(COURSE_WRITE.name())
                                .antMatchers(PUT, COURSE_URL).hasAuthority(COURSE_WRITE.name())
                                .antMatchers(GET, COURSE_URL).hasAuthority(COURSE_READ.name())
                )
                .httpBasic(withDefaults());
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .inMemoryAuthentication()
                .withUser("smith").password(encoder.encode("123")).roles(ADMIN.name()).authorities(ADMIN.getAuthorities())
                .and()
                .withUser("joe").password(encoder.encode("123")).roles(MANAGER.name()).authorities(MANAGER.getAuthorities());
    }
}
