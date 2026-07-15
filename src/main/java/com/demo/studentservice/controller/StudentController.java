package com.demo.studentservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.demo.studentservice.entity.Student;
import com.demo.studentservice.repository.StudentRepository;
import com.demo.studentservice.entity.StudentRequest;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/students")
@Validated
public class StudentController {

    private final StudentRepository repo;

    public StudentController(StudentRepository repo) {
        this.repo = repo;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Student addStudent(@Valid @RequestBody StudentRequest request) {

        if (repo.existsByStudentId(request.getStudentId())) {
            throw new RuntimeException("StudentId already exists: " + request.getStudentId());
        }

        Student s = new Student();
        s.setStudentId(request.getStudentId());
        s.setStudentName(request.getStudentName());
        s.setGrade(request.getGrade());
        s.setMobileNumber(request.getMobileNumber());
        s.setSchoolName(request.getSchoolName());

        return repo.save(s);
    }

    @GetMapping("/{studentId}")
    public Student getStudent(@PathVariable String studentId) {
        return repo.findByStudentId(studentId)
                .orElseThrow(() -> new RuntimeException("Student not found: " + studentId));
    }

    @GetMapping
    public List<Student> listStudents() {
        return repo.findAll();
    }
}