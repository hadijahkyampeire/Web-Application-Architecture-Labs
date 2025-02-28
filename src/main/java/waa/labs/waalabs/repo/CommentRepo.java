package waa.labs.waalabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import waa.labs.waalabs.domain.Comment;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Long> {
}
