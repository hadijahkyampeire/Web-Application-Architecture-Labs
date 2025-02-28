package waa.labs.waalabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.labs.waalabs.domain.Logger;

@Repository
public interface LoggerRepo extends JpaRepository<Logger, Long> {
}
