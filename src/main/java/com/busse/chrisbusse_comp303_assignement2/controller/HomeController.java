package com.busse.chrisbusse_comp303_assignement2.controller;

import com.busse.chrisbusse_comp303_assignement2.model.Enrollment;
import com.busse.chrisbusse_comp303_assignement2.model.Student;
import com.busse.chrisbusse_comp303_assignement2.service.EnrollmentService;
import com.busse.chrisbusse_comp303_assignement2.service.StudentService;
import com.busse.chrisbusse_comp303_assignement2.service.ProgramService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;


@Controller
public class HomeController {
    private final StudentService studentService;
    private final EnrollmentService enrollmentService;


    public HomeController(StudentService studentService, EnrollmentService enrollmentService) {
        this.studentService = studentService;
        this.enrollmentService = enrollmentService;
    }

    @Autowired
    private ProgramService programService;


    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("student", new Student());
        return "index";
    }

    @PostMapping("/login")
    public String login(@RequestParam String userName,
                        @RequestParam String password,
                        HttpSession session,
                        RedirectAttributes redirectAttributes) {
        try {
            Student student = studentService.authenticate(userName, password);
            session.setAttribute("student", student);
            return "redirect:/programs";
        } catch (RuntimeException e) {
            redirectAttributes.addFlashAttribute("loginError", e.getMessage());
            return "redirect:/?error=true";
        }
    }

    @GetMapping("/programs")
    public String programs(HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/";
        }
        model.addAttribute("student", new Student());
        model.addAttribute("programs", programService.getAllPrograms());
        return "programs";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute Student student,
                           BindingResult result,
                           HttpSession session,
                           RedirectAttributes redirectAttributes) {
        if (result.hasErrors()) {
            redirectAttributes.addFlashAttribute("registerErrors", result.getAllErrors());
            return "redirect:/?dialog=register";
        }

        try {
            Student registeredStudent = studentService.registerStudent(student);
            session.setAttribute("student", registeredStudent);
            return "redirect:/programs";
        } catch (Exception e) {
            redirectAttributes.addFlashAttribute("registerError", e.getMessage());
            return "redirect:/?dialog=register";
        }
    }

    @GetMapping("/profile")
    public String profile(Model model, HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/";
        }
        List<Enrollment> enrollments = enrollmentService.getStudentEnrollments(student);
        model.addAttribute("student", student);
        model.addAttribute("enrollments", enrollments);
        return "profile";
    }

    @PostMapping("/profile/update")
    public String updateProfile(@Valid Student student, BindingResult result, Model model, HttpSession session) {
        if (result.hasErrors()) {
            System.out.println(result.getAllErrors());
            return "profile";
        }
        studentService.updateProfile(student.getStudentId(), student);
        session.setAttribute("student", student);
        model.addAttribute("student", student);
        return "redirect:/profile";
    }

    @ExceptionHandler(Exception.class)
    public String handleError(Exception e, Model model) {
        model.addAttribute("errorMessage", e.getMessage());
        return "error";
    }
}