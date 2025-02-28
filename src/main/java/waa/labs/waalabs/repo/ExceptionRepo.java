package waa.labs.waalabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.labs.waalabs.domain.ExceptionLogger;

@Repository
public interface ExceptionRepo extends JpaRepository<ExceptionLogger, Long> {
}
