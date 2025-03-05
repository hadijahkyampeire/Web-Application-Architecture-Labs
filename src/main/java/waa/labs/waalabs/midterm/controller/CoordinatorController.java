package waa.labs.waalabs.midterm.controller;

import org.springframework.web.bind.annotation.*;
import waa.labs.waalabs.midterm.entity.Coordinator;
import waa.labs.waalabs.midterm.entity.Event;
import waa.labs.waalabs.midterm.entity.Task;
import waa.labs.waalabs.midterm.repo.CoordinatorRepo;

import java.util.List;

@RestController
@RequestMapping("/api/v1/coordinator")
@CrossOrigin(origins = "*")
public class CoordinatorController {
    private CoordinatorRepo coordinatorRepo;
    public CoordinatorController(CoordinatorRepo coordinatorRepo) {
        this.coordinatorRepo = coordinatorRepo;
    }

    @GetMapping
    public List<Coordinator> findAll() {
        return coordinatorRepo.findAll();
    }

    @GetMapping("/{id}")
    public Coordinator findById(long id) {
        return coordinatorRepo.findById(id).orElse(null);
    }

    @PostMapping
    public Coordinator save(@RequestBody Coordinator coordinator) {
        return coordinatorRepo.save(coordinator);
    }

    @PutMapping("/{id}")
    public Coordinator update(@PathVariable long id, @RequestBody Coordinator coordinator) {
        coordinator.setId(id);
        return coordinatorRepo.save(coordinator);
    }

    @DeleteMapping("/{id}")
    public void deleteById(long id) {
        coordinatorRepo.deleteById(id);
    }

    @GetMapping("/{id}/events")
    public List<Event> getCoordinatorEvents(@PathVariable Long id) {
        return coordinatorRepo.getCoordinatorEvents(id);
    }

    @GetMapping("/{id}/tasks")
    public List<Task> getCoordinatorTasks(@PathVariable Long id) {
        return coordinatorRepo.getCoordinatorTasks(id);
    }

    @GetMapping("/eventsOutOfState")
    public List<Coordinator> getAllCoordinatorsWithEventsNotInTheStateTheyLiveIn() {
        return coordinatorRepo.getAllCoordinatorsWithEventsNotInTheStateTheyLiveIn();
    }

    @GetMapping("/filter")
    public List<Coordinator> filterCoordinators(@RequestParam(value="gender") String gender,
                                                @RequestParam(value="name") String name) {
        if(gender != null && name != null) {
            return coordinatorRepo.filterByGenderAndName(gender, name);
        } else if(gender != null) {
            return coordinatorRepo.filterByGender(gender);
        } else if(name != null) {
            return coordinatorRepo.filterByName(name);
        } else {
            return coordinatorRepo.findAll();
        }
    }
}
