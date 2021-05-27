package com.github.faris.security.httpbasic.controller;

import com.github.faris.security.httpbasic.model.Course;
import com.github.faris.security.httpbasic.model.Student;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/httpbasic")
public class HttpBasicController {

    private static final List<Student> STUDENTS = new ArrayList<>(List.of(
            new Student(1L, "Smith", 20),
            new Student(2L, "Joe", 22),
            new Student(3L, "Jeniffer", 27)
    ));

    private static final List<Course> COURSES = new ArrayList<>(List.of(
            new Course(1L, "Java"),
            new Course(2L, "PHP"),
            new Course(3L, "Python"),
            new Course(4L, "C#")
    ));

    @GetMapping("info")
    public String info() {
        return "http basic";
    }

    @GetMapping("profile")
    public String profile() {
        return "I am Faris.";
    }

    @GetMapping(value = "students", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Student> getStudents() {
        return STUDENTS;
    }

    @GetMapping(value = "students/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Student> getStudent(@PathVariable("id") Long id) {
        return STUDENTS
                .stream()
                .filter(s -> s.getId().equals(id))
                .map(ResponseEntity::ok)
                .findFirst()
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping(value = "students", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> registerStudent(@RequestBody Student student) {
        STUDENTS.add(student);
        return ResponseEntity.created(URI.create("student/" + student.getId())).build();
    }

    @GetMapping(value = "courses", produces = MediaType.APPLICATION_JSON_VALUE)
    public List<Course> getCourses() {
        return COURSES;
    }

    @GetMapping(value = "courses/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Course> getCourse(@PathVariable("id") Long id) {
        return COURSES
                .stream()
                .filter(s -> s.getId().equals(id))
                .map(ResponseEntity::ok)
                .findFirst()
                .orElse(ResponseEntity.notFound().build());
    }
    
    @PostMapping(value = "courses", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        COURSES.add(course);
        return ResponseEntity.created(URI.create("courses/" + course.getId())).build();
    }
}
