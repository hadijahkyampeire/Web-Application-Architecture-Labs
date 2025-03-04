package waa.labs.waalabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import waa.labs.waalabs.domain.Role;

import java.util.Optional;

@Repository
public interface RoleRepo extends JpaRepository<Role, Long> {
    @Query("SELECT r FROM Role r WHERE r.role = :name")
    Role findRoleByName(@Param("name")String name);
}
