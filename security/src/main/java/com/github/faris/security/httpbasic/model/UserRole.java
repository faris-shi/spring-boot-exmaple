package com.github.faris.security.httpbasic.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;
import java.util.Set;

import static com.github.faris.security.httpbasic.model.UserAuthority.COURSE_READ;
import static com.github.faris.security.httpbasic.model.UserAuthority.STUDENT_READ;
import static java.util.stream.Collectors.toList;

public enum UserRole {

    ADMIN(Set.of(UserAuthority.values())),

    MANAGER(Set.of(COURSE_READ, STUDENT_READ));

    private final Set<UserAuthority> authorities;

    UserRole(Set<UserAuthority> authorities) {
        this.authorities = authorities;
    }

    public List<GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> list = authorities.stream().map(UserAuthority::name).map(SimpleGrantedAuthority::new).collect(toList());
        list.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return list;
    }
}
