package com.busse.chrisbusse_comp303_assignement2.controller;

import com.busse.chrisbusse_comp303_assignement2.model.*;
import com.busse.chrisbusse_comp303_assignement2.repository.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.time.LocalDate;


@Controller
@RequestMapping("/enrollment")
public class EnrollmentController {
    @Autowired private EnrollmentRepository enrollmentRepository;
    @Autowired private ProgramRepository programRepository;

    @GetMapping("/enroll")
    public String showEnrollmentForm(@RequestParam String programCode, Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/";
        }

        Program program = programRepository.findById(programCode)
                .orElseThrow(() -> new IllegalArgumentException("Invalid program code:" + programCode));

        Enrollment enrollment = new Enrollment();
        enrollment.setStudent(student);
        enrollment.setProgram(program);
        enrollment.setStartDate(LocalDate.now());

        model.addAttribute("enrollment", enrollment);
        model.addAttribute("program", program);
        model.addAttribute("student", student); // Add this line

        return "enrollment";
    }

    @PostMapping("/enroll")
    public String enrollStudent(@Valid Enrollment enrollment,
                                BindingResult result,
                                Model model,
                                HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/";
        }

        if (result.hasErrors()) {
            model.addAttribute("program", enrollment.getProgram());
            model.addAttribute("student", student);
            return "enrollment";
        }
        enrollment.setAmountPaid(enrollment.getProgram().getFee());
        enrollmentRepository.save(enrollment);

        model.addAttribute("enrollment", enrollment);
        model.addAttribute("student", student);

        return "confirmation";
    }
}