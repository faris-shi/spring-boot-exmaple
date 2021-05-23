package com.github.faris.springboot.security.basic.repo;

import com.github.faris.springboot.security.basic.entity.Permission;
import com.github.faris.springboot.security.basic.entity.Role;
import com.github.faris.springboot.security.basic.entity.User;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class DbInit implements CommandLineRunner {

    private final UserRepository userRepository;

    private final RoleRepository roleRepository;

    private final PermissionRepository permissionRepository;

    private final PasswordEncoder passwordEncoder;

    public DbInit(UserRepository userRepository, RoleRepository roleRepository,
                  PermissionRepository permissionRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        userRepository.deleteAll();
        roleRepository.deleteAll();
        permissionRepository.deleteAll();

        Permission p1 = buildPermission("REST_TEST1");
        Permission p2 = buildPermission("USER_PROFILE");
        Permission p3 = buildPermission("REST_TEST2");

        Role admin = buildRole("ADMIN", p1, p2, p3);
        Role manager = buildRole("MANAGER", p1, p3);
        Role user = buildRole("USER", p2);


        User faris = new User("faris", passwordEncoder.encode("123"), user.getId().toString());
        User rachel = new User("rachel", passwordEncoder.encode("123"), admin.getId().toString());
        User helen = new User("helen", passwordEncoder.encode("123"), manager.getId().toString());

        List<User> users = Arrays.asList(faris, rachel, helen);
        userRepository.saveAll(users);
        System.out.println("------db done------");
    }

    private Permission buildPermission(String name) {
        return permissionRepository.save(new Permission(name));
    }

    private Role buildRole(String name, Permission... permissions) {
        return roleRepository.save(new Role(name, new ArrayList<Permission>(Arrays.asList(permissions))));
    }
}
