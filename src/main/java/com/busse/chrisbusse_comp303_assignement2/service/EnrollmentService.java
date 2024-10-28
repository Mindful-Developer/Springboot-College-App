package com.busse.chrisbusse_comp303_assignement2.service;

import com.busse.chrisbusse_comp303_assignement2.model.Enrollment;
import com.busse.chrisbusse_comp303_assignement2.model.Program;
import com.busse.chrisbusse_comp303_assignement2.model.Student;
import com.busse.chrisbusse_comp303_assignement2.repository.EnrollmentRepository;
import com.busse.chrisbusse_comp303_assignement2.repository.ProgramRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class EnrollmentService {
    @Autowired private EnrollmentRepository enrollmentRepository;
    @Autowired private ProgramRepository programRepository;

    @Transactional
    public Enrollment createEnrollment(Enrollment enrollment) {
        Program program = programRepository.findById(enrollment.getProgram().getProgramCode())
                .orElseThrow(() -> new RuntimeException("Program not found"));
        enrollment.setAmountPaid(program.getFee());
        processPayment(enrollment);

        return enrollmentRepository.save(enrollment);
    }

    private void processPayment(Enrollment enrollment) {
        if (!isValidCardNumber(enrollment.getCardNumber())) {
            throw new RuntimeException("Invalid card number");
        }
    }

    private boolean isValidCardNumber(String cardNumber) {
        System.out.println("Validating card number: " + cardNumber);
        return cardNumber.length() == 19 && cardNumber.matches("\\d{4}\\s*\\d{4}\\s*\\d{4}\\s*\\d{4}");
    }

    public List<Enrollment> getStudentEnrollments(Student student) {
        return enrollmentRepository.findByStudent(student);
    }
}