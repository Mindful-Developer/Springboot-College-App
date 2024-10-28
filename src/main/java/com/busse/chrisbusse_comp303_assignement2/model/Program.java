package com.busse.chrisbusse_comp303_assignement2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;


@Entity
@Table(name = "programs")
public class Program implements Serializable {
    @Id
    @Column(length = 10)
    private String programCode;

    @NotBlank(message = "Program name is required")
    private String programName;

    @Min(value = 1, message = "Duration must be at least 1 semester")
    private Integer duration;

    @DecimalMin(value = "0.0", message = "Fee must be positive")
    private Double fee;

    @NotBlank(message = "Professor name is required")
    private String professor;

    public Program() {}

    public Program(String programCode, String programName, int duration, double fee, String professor) {
        this.programCode = programCode;
        this.programName = programName;
        this.duration = duration;
        this.fee = fee;
        this.professor = professor;
    }

    public String getProgramCode() {
        return programCode;
    }

    public String getProgramName() {
        return programName;
    }

    public Integer getDuration() {
        return duration;
    }

    public Double getFee() {
        return fee;
    }

    public String getProfessor() {
        return professor;
    }

    public void setProgramCode(String programCode) {
        this.programCode = programCode;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public void setFee(Double fee) {
        this.fee = fee;
    }

    public void setProfessor(String professor) {
        this.professor = professor;
    }

    @Override
    public String toString() {
        return "Program{" +
                "programCode='" + programCode + '\'' +
                ", programName='" + programName + '\'' +
                ", duration=" + duration +
                ", fee=" + fee +
                ", professor='" + professor + '\'' +
                '}';
    }
}