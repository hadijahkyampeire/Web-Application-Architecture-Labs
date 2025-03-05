package waa.labs.waalabs.midterm.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Coordinator {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long co_id;
    private String name;
    private String gender;

    @ManyToMany
    @JoinTable(name = "coordinators_events", joinColumns = @JoinColumn(name = "co_id"), inverseJoinColumns = @JoinColumn(name = "event_id"))
    List<Event> events;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "address_id")
    private Address address;

    public Coordinator() {}


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public List<Event> getEvents() {
        return events;
    }

    public void setEvents(List<Event> events) {
        this.events = events;
    }

    public void setId(Long id) {
        this.co_id = id;
    }

    public Long getId() {
        return co_id;
    }
}
