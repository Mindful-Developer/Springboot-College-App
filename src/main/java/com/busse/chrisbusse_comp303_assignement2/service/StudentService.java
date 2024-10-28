package com.busse.chrisbusse_comp303_assignement2.service;

import com.busse.chrisbusse_comp303_assignement2.model.Student;
import com.busse.chrisbusse_comp303_assignement2.repository.StudentRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class StudentService {
    private final StudentRepository studentRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @PostConstruct
    public void initializeTestUser() {
        if (!studentRepository.existsByUserName("test")) {
            Student testStudent = new Student();
            testStudent.setUserName("test");
            testStudent.setPassword(passwordEncoder.encode("test123")); // Password will be "test123"
            testStudent.setFirstName("Test");
            testStudent.setLastName("User");
            testStudent.setAddress("123 Test St");
            testStudent.setCity("Toronto");
            testStudent.setPostalCode("M1M 1M1");
            studentRepository.save(testStudent);
        }
    }

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = new BCryptPasswordEncoder();
    }

    public Student authenticate(String userName, String password) {
        System.out.println("Attempting to authenticate user: " + userName); // Add this line

        Student student = studentRepository.findByUserName(userName)
                .orElseThrow(() -> {
                    System.out.println("User not found: " + userName); // Add this line
                    return new RuntimeException("Invalid username or password");
                });

        if (!passwordEncoder.matches(password, student.getPassword())) {
            System.out.println("Password mismatch for user: " + userName); // Add this line
            throw new RuntimeException("Invalid username or password");
        }

        System.out.println("Authentication successful for user: " + userName); // Add this line
        return student;
    }

    public Student registerStudent(Student student) {
        if (studentRepository.existsByUserName(student.getUserName())) {
            throw new RuntimeException("Username already exists");
        }

        student.setPassword(passwordEncoder.encode(student.getPassword()));
        return studentRepository.save(student);
    }

    public Student updateProfile(Long id, Student updatedStudent) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setFirstName(updatedStudent.getFirstName());
                    student.setLastName(updatedStudent.getLastName());
                    student.setAddress(updatedStudent.getAddress());
                    student.setCity(updatedStudent.getCity());
                    student.setPostalCode(updatedStudent.getPostalCode());
                    student.setTechnicalSkills(updatedStudent.getTechnicalSkills());
                    return studentRepository.save(student);
                })
                .orElseThrow(() -> new RuntimeException("Student not found"));
    }
}