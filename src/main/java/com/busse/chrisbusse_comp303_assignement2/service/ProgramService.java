package com.busse.chrisbusse_comp303_assignement2.service;

import com.busse.chrisbusse_comp303_assignement2.model.Program;
import com.busse.chrisbusse_comp303_assignement2.repository.ProgramRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;


@Service
public class ProgramService {
    @Autowired
    private ProgramRepository programRepository;

    public List<Program> getAllPrograms() {
        return programRepository.findAll();
    }

    public Program getProgram(String programCode) {
        return programRepository.findById(programCode)
                .orElseThrow(() -> new RuntimeException("Program not found"));
    }

    @PostConstruct
    public void initializePrograms() {
        if (programRepository.count() == 0) {
            List<Program> defaultPrograms = Arrays.asList(
                    new Program("3408", "Software Engineering Technician", 4, 4013.69 * 2, "Predrag Pesikan"),
                    new Program("3409", "Software Engineering Technology", 6, 4013.69 * 3, "Predrag Pesikan"),
                    new Program("3402", "Artificial Intelligence - Software Engineering Technology", 6, 4318.69 * 3, "Predrag Pesikan"),
                    new Program("3109", "Game - Programming", 6, 4013.69 * 3, "Predrag Pesikan"),
                    new Program("3223", "Mobile Applications Development", 2, 5640.74, "Predrag Pesikan"),
                    new Program("3508", "Health Informatics Technology", 6, 4013.69 * 3, "Predrag Pesikan"),
                    new Program("3404", "Computer Systems Technician - Networking", 4, 4671.19 * 2, "Predrag Pesikan"),
                    new Program("3224", "Cybersecurity", 2, 6355.74, "Predrag Pesikan")
            );
            programRepository.saveAll(defaultPrograms);
        }
    }
}