package com.busse.chrisbusse_comp303_assignement2.repository;

import com.busse.chrisbusse_comp303_assignement2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


public interface StudentRepository extends JpaRepository<Student, Long> {
    Optional<Student> findByUserName(String userName);
    boolean existsByUserName(String userName);
}