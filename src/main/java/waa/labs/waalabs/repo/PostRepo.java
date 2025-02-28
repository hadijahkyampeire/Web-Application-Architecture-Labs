package waa.labs.waalabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import waa.labs.waalabs.domain.Post;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Long> {


    // For non persistent
//    List<Post> findAll();
//    Post findById(long id);
//    void save(Post post);
//    void delete(long id);
//    void update(long id, Post post);
//    List<Post> filterByAuthor(String authorName);

    // Partial Match, case-insensitive
    @Query("SELECT p FROM Post p WHERE LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%') )")
    List<Post> findPostsByTitle(@Param("title") String title);

    // case-insensitive
    @Query("SELECT p FROM Post p WHERE LOWER(p.author) LIKE LOWER(CONCAT('%', :author, '%')) AND LOWER(p.title) LIKE LOWER(CONCAT('%', :title, '%'))")
    List<Post> findByAuthorAndTitle(@Param("author") String author, @Param("title") String title);
}
