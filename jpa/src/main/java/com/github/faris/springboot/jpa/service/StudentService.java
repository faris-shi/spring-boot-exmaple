package com.github.faris.springboot.jpa.service;

import com.github.faris.springboot.jpa.entity.Student;
import com.github.faris.springboot.jpa.entity.meta.Student_;
import com.github.faris.springboot.jpa.repo.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Service
public class StudentService {

    private StudentRepository studentRepository;

    public List<Student> search() {
        Student student = new Student();
        student.setAge(10);
        student.setEmail("faris.shi84@gmail.com");
        student.setFirstName("Faris");
        student.setLastName("Shi");

        ExampleMatcher matcher = ExampleMatcher.matching()
                .withStringMatcher(StringMatcher.CONTAINING)
                .withIgnoreCase(true)
                .withMatcher("firstName", GenericPropertyMatchers.startsWith())
                .withIgnorePaths("age");
        return studentRepository.findAll(Example.of(student, matcher), Sort.by(Sort.Direction.DESC, "id"));
    }

    public List<Student> findByConditions() {
        Student student = new Student();
        student.setAge(10);
        student.setEmail("faris.shi84@gmail.com");
        student.setFirstName("Faris");
        student.setLastName("Shi");

        return studentRepository.findAll(criteria(student));
    }

    private static Specification<Student> criteria(Student student) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (StringUtils.hasText(student.getFirstName())) {
                predicates.add(cb.like(root.get(Student_.firstName), "%" + student.getFirstName() + "%"));
            }
            if (StringUtils.hasText(student.getEmail())) {
                predicates.add(cb.equal(root.get(Student_.email), student.getEmail()));
            }
            if (student.getAge() != null) {
                predicates.add((cb.equal(root.get(Student_.age), student.getAge())));
            }
            return query.where(predicates.toArray(new Predicate[0])).getRestriction();
        };
    }

    @Autowired
    public StudentRepository getStudentRepository() {
        return studentRepository;
    }
}
