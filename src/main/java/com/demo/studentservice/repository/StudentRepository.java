package com.demo.studentservice.repository;

//import studentservice.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import com.demo.studentservice.entity.Student;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByStudentId(String studentId);
    boolean existsByStudentId(String studentId);
}