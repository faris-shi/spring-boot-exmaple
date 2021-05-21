package com.github.faris.springboot.security.basic.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(
        name = "tbl_permission",
        indexes = {
                @Index(name = "tbl_permission_role_idx", columnList = "role")
        },
        uniqueConstraints = {
                @UniqueConstraint(name = "tbl_permission_name_uk", columnNames = "name")
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "role", nullable = false)
    private String role;

    @CreatedDate
    @Column(name = "created_at")
    private LocalDateTime createdAt;
}
