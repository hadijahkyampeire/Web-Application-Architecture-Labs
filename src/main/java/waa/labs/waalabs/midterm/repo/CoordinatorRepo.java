package waa.labs.waalabs.midterm.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import waa.labs.waalabs.midterm.entity.Coordinator;
import waa.labs.waalabs.midterm.entity.Event;
import waa.labs.waalabs.midterm.entity.Task;

import java.util.List;

@Repository
public interface CoordinatorRepo extends JpaRepository<Coordinator, Long> {
    @Query("SELECT e FROM Coordinator c JOIN c.events e WHERE c.co_id= :coordinatorId")
    List<Event> getCoordinatorEvents(@Param("coordinatorId") Long coordinatorId);

    @Query("SELECT t FROM Coordinator c JOIN c.events e JOIN e.tasks t WHERE c.co_id= :coordinatorId")
    List<Task> getCoordinatorTasks(@Param("coordinatorId") Long coordinatorId);

    @Query("SELECT c FROM Coordinator c JOIN c.address a JOIN c.events e WHERE a.state != e.state")
    List<Coordinator> getAllCoordinatorsWithEventsNotInTheStateTheyLiveIn();

    @Query("SELECT c FROM Coordinator c WHERE c.gender= :gender")
    List<Coordinator> filterByGender(@Param("gender") String gender);

    @Query("SELECT c FROM Coordinator c WHERE c.name LIKE %:name%")
    List<Coordinator> filterByName(@Param("name") String name);

    @Query("SELECT c FROM Coordinator c WHERE c.gender= :gender AND c.name LIKE %:name%")
    List<Coordinator> filterByGenderAndName(@Param("gender") String gender, @Param("name") String name);
}
