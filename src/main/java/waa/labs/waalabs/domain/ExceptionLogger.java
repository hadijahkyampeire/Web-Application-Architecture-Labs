package waa.labs.waalabs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class ExceptionLogger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long transactionId;
    private LocalDate date;
    private LocalTime time;
    private String principle;
    private String operation;
    private String exceptionType;

    public ExceptionLogger() {}

    public ExceptionLogger(LocalDate date, LocalTime time, String principle, String operation, String exceptionType) {
        this.date = date;
        this.time = time;
        this.principle = principle;
        this.operation = operation;
        this.exceptionType = exceptionType;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getTime() {
        return time;
    }

    public String getPrinciple() {
        return principle;
    }

    public String getOperation() {
        return operation;
    }

    public String getExceptionType() {
        return exceptionType;
    }



}
