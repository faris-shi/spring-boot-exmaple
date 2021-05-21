package com.github.faris.springboot.security.basic.repo;

import com.github.faris.springboot.security.basic.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PermissionRepository extends JpaRepository<Permission, Long> {

    List<Permission> findByRole(String role);
}
