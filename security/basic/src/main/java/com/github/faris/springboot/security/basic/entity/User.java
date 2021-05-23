package com.github.faris.springboot.security.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Entity
@Table(name = "tbl_user")
@Data
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "username", nullable = false, unique = true)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "role_ids")
    private String roleIds;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public User(String username, String password, String roleIds) {
        this.username = username;
        this.password = password;
        this.roleIds = roleIds;
    }

    public List<Long> getRoleIdArrays() {
        if (!StringUtils.hasText(roleIds)) {
            return Collections.emptyList();
        }
        return Arrays.stream(roleIds.split(",")).map(Long::valueOf).collect(toList());
    }
}
