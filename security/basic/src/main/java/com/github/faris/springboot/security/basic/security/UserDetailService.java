package com.github.faris.springboot.security.basic.security;

import com.github.faris.springboot.security.basic.entity.Permission;
import com.github.faris.springboot.security.basic.entity.User;
import com.github.faris.springboot.security.basic.repo.PermissionRepository;
import com.github.faris.springboot.security.basic.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

//@Service
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    private final PermissionRepository permissionRepository;

    public UserDetailService(UserRepository userRepository, PermissionRepository permissionRepository) {
        this.userRepository = userRepository;
        this.permissionRepository = permissionRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = userRepository.findByUsername(username);
        User user = userOptional.orElseThrow(() -> new UsernameNotFoundException(""));

        List<String> roles = user.roles();
        List<String> permissions = roles.stream().map(permissionRepository::findByRole)
                .flatMap(Collection::stream)
                .map(Permission::getName).collect(toList());
        roles = roles.stream().map(r -> "ROLE_" + r).collect(toList());
        roles.addAll(permissions);
        List<GrantedAuthority> authorities = roles.stream().map(SimpleGrantedAuthority::new).collect(toList());
        return new UserPrincipal(user.getUsername(), user.getPassword(), true, authorities);
    }
}