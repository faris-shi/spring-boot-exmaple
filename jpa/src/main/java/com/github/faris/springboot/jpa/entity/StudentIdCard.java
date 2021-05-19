package com.github.faris.springboot.jpa.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(
        name = "tbl_student_id_card",
        uniqueConstraints = @UniqueConstraint(
                name = "student_id_card_card_number_uk",
                columnNames = "card_number"
        )
)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class StudentIdCard {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "student_id_card")
    @SequenceGenerator(name = "student_id_card", sequenceName = "student_id_card")
    @Column(name = "id", updatable = false)
    private Long id;

    @Column(name = "card_number", unique = true, updatable = false, nullable = false, length = 15)
    private String cardNumber;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(
            name = "student_id",
            referencedColumnName = "id",
            foreignKey = @ForeignKey(name = "student_id_card_student_id_fk")
    )
    private Student student;
}
