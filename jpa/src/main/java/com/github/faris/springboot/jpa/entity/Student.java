package com.github.faris.springboot.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(
        name = "tbl_student",
        uniqueConstraints = @UniqueConstraint(
                name = "student_email_uk",
                columnNames = "email"
        )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_sequence")
    @SequenceGenerator(name = "student_sequence", sequenceName = "student_sequence")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "first_name", nullable = false, length = 20)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 20)
    private String lastName;

    @Column(name = "email", nullable = false, unique = true, length = 100)
    private String email;

    @Column(name = "age", nullable = false)
    private Integer age;

    @OneToOne(
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            orphanRemoval = true,
            mappedBy = "student"
    )
    private StudentIdCard studentIdCard;

    @OneToMany(
            cascade = {CascadeType.REMOVE, CascadeType.PERSIST},
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "student")
    private List<Book> books;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            orphanRemoval = true,
            fetch = FetchType.LAZY,
            mappedBy = "student"
    )
    private List<Enrollment> enrollments;

    @Version
    @Column(name = "version", nullable = false)
    private Long version;
}
