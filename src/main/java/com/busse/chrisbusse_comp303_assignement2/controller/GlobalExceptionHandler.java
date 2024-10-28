package com.busse.chrisbusse_comp303_assignement2.controller;

import com.busse.chrisbusse_comp303_assignement2.model.Student;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception e, Model model) {
        model.addAttribute("message", e.getMessage());
        model.addAttribute("student", new Student());
        return "error";
    }
}