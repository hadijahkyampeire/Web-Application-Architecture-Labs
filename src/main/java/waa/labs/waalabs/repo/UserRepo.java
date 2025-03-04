package waa.labs.waalabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import waa.labs.waalabs.domain.Comment;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.dto.CommentDto;
import waa.labs.waalabs.dto.UserDto;

import java.util.List;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
//    List<UserDto> findAll();

    @Query("SELECT u FROM User u JOIN u.posts p GROUP BY u HAVING COUNT(p) > 1")
    List<User> findUsersWithMoreThanOnePost();

    @Query("SELECT u FROM User u JOIN u.posts p GROUP BY u HAVING COUNT(p) > :n")
    List<User> findUsersWithMoreThanNPost(@Param("n") int n);

    @Query("SELECT u FROM User u JOIN u.posts p WHERE LOWER(p.title) LIKE(CONCAT('%', :title, '%')) ")
    List<User> findUserPostsByTitle(String title);

    @Query("SELECT c FROM User u JOIN u.posts p JOIN p.comments c WHERE u.id = :userId and p.id = :postId and c.id = :commentId")
    Comment findUserPostCommentById(long userId, long postId, long commentId);

    @Query("SELECT p FROM User u JOIN u.posts p WHERE u.id = :userId and p.id = :postId")
    Post findUserPostById(long userId, long postId);

    @Query("SELECT u FROM User u WHERE LOWER(u.email) =:email")
    User findUserByEmail(@Param("email") String email);
}
