package com.busse.chrisbusse_comp303_assignement2.repository;

import com.busse.chrisbusse_comp303_assignement2.model.Program;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProgramRepository extends JpaRepository<Program, String> {
}