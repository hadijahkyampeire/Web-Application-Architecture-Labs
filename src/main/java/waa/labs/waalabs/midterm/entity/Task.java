package waa.labs.waalabs.midterm.entity;

import jakarta.persistence.*;

@Entity
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long task_id;
    private String description;

    @ManyToOne
    @JoinColumn(name = "event_id")
    private Event event;

    public Task() {}
    public Task(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.task_id = id;
    }

    public Long getId() {
        return task_id;
    }
}
