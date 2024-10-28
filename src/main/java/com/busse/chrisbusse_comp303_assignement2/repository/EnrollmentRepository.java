package com.busse.chrisbusse_comp303_assignement2.repository;

import com.busse.chrisbusse_comp303_assignement2.model.Enrollment;
import com.busse.chrisbusse_comp303_assignement2.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


public interface EnrollmentRepository extends JpaRepository<Enrollment, Long> {
    List<Enrollment> findByStudent(Student student);
}