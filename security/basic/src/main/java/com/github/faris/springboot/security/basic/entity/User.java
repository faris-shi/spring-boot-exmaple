package com.github.faris.springboot.security.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.util.StringUtils;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Entity
@Table(
        name = "tbl_user",
        uniqueConstraints = {
                @UniqueConstraint(name = "tbl_user_username_uk", columnNames = "username")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "username", unique = true, nullable = false)
    private String username;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "roles")
    private String roles;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;

    public List<String> roles() {
        if (!StringUtils.hasText(this.roles)) {
            return Collections.emptyList();
        }
        return new ArrayList<>(List.of(roles.split(",")));
    }
}
