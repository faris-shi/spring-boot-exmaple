package com.github.faris.springboot.security.basic.repo;

import com.github.faris.springboot.security.basic.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
}
