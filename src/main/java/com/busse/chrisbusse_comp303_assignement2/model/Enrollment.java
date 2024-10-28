package com.busse.chrisbusse_comp303_assignement2.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;


@Entity
@Table(name = "enrollments")
public class Enrollment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long applicationNo;

    @ManyToOne
    @JoinColumn(name = "studentId", nullable = false)
    private Student student;

    @ManyToOne
    @JoinColumn(name = "programCode", nullable = false)
    private Program program;

    @NotNull(message = "Start date is required")
    private LocalDate startDate;

    @DecimalMin(value = "0.0", message = "Amount paid must be positive")
    private Double amountPaid;

    @Column(length = 19)
    @Pattern(regexp = "\\d{4}\\s*\\d{4}\\s*\\d{4}\\s*\\d{4}", message = "Invalid card number")
    private String cardNumber;

    @Column(length = 5)
    @Pattern(regexp = "\\d{2}/\\d{2}", message = "Invalid expiry date format (MM/YY)")
    private String cardExpiry;

    @Column(length = 3)
    @Pattern(regexp = "\\d{3}", message = "Invalid CVV")
    private String cardCVV;

    private LocalDate createdAt;

    // Default constructor
    public Enrollment() {
        this.createdAt = LocalDate.now();
    }

    // Getters
    public Long getApplicationNo() {
        return applicationNo;
    }

    public Student getStudent() {
        return student;
    }

    public Program getProgram() {
        return program;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public Double getAmountPaid() {
        return amountPaid;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getCardExpiry() {
        return cardExpiry;
    }

    public String getCardCVV() {
        return cardCVV;
    }

    public LocalDate getCreatedAt() {
        return createdAt;
    }

    // Setters
    public void setApplicationNo(Long applicationNo) {
        this.applicationNo = applicationNo;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public void setProgram(Program program) {
        this.program = program;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public void setAmountPaid(Double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public void setCardExpiry(String cardExpiry) {
        this.cardExpiry = cardExpiry;
    }

    public void setCardCVV(String cardCVV) {
        this.cardCVV = cardCVV;
    }

    public void setCreatedAt(LocalDate createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "Enrollment{" +
                "applicationNo=" + applicationNo +
                ", student=" + student.getFullName() +
                ", program=" + program.getProgramName() +
                ", startDate=" + startDate +
                ", amountPaid=" + amountPaid +
                ", createdAt=" + createdAt +
                '}';
    }
}