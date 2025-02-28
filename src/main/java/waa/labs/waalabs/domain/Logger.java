package waa.labs.waalabs.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class Logger {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long transactionalId;
    private LocalDate date;
    private LocalTime time;
    private String principle;
    private String operation;

    public Logger() {}

    public Logger(LocalDate date, LocalTime time, String principle, String operation) {
        this.date = date;
        this.time = time;
        this.principle = principle;
        this.operation = operation;
    }
    public void setId(Long id) {
        this.transactionalId = id;
    }

    public long getTransactionalId() {
        return transactionalId;
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

}
