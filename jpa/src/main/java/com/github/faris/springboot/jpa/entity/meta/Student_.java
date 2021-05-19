package com.github.faris.springboot.jpa.entity.meta;

import com.github.faris.springboot.jpa.entity.Student;
import com.github.faris.springboot.jpa.entity.StudentIdCard;

import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@StaticMetamodel(Student_.class)
public class Student_ {

    public static volatile SingularAttribute<Student, Long> id;

    public static volatile SingularAttribute<Student, String> firstName;

    public static volatile SingularAttribute<Student, String> email;

    public static volatile SingularAttribute<Student, Integer> age;

    public static volatile SingularAttribute<Student, StudentIdCard> studentIdCard;
}
