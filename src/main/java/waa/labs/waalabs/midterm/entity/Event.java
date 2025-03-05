package waa.labs.waalabs.midterm.entity;

import jakarta.persistence.*;
import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import java.util.List;

@Entity
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long event_id;
    private String title;
    private String state;

    @ManyToMany(mappedBy = "events")
    List<Coordinator> coordinators;

    @OneToMany(mappedBy = "event", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @Fetch(FetchMode.JOIN)
    List<Task> tasks;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public Event() {}

    public void setId(Long id) {
        this.event_id = id;
    }

    public Long getId() {
        return event_id;
    }
}
