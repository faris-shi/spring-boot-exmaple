package com.github.faris.springboot.security.basic.security;

import com.github.faris.springboot.security.basic.entity.Permission;
import com.github.faris.springboot.security.basic.entity.Role;
import com.github.faris.springboot.security.basic.entity.User;
import com.github.faris.springboot.security.basic.repo.RoleRepository;
import com.github.faris.springboot.security.basic.repo.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    public UserPrincipalDetailsService(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(s).orElseThrow(() -> new UsernameNotFoundException("user not exist"));

        List<Role> roles = new ArrayList<>();
        user.getRoleIdArrays().forEach(roleId -> {
            roleRepository.findById(roleId).ifPresent(roles::add);
        });

        List<String> permissions = roles.stream().map(Role::getPermissions).flatMap(Collection::stream).map(Permission::getName).collect(toList());
        List<String> roleNames = roles.stream().map(role -> "ROLE_" + role.getName()).collect(toList());
        roleNames.addAll(permissions);
        System.out.println(roleNames);
        return new UserPrincipal(user.getUsername(), user.getPassword(), roleNames.stream().map(SimpleGrantedAuthority::new).collect(toList()));
    }
}
