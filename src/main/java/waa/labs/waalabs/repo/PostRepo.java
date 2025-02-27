package waa.labs.waalabs.repo;

import org.springframework.data.jpa.repository.JpaRepository;
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
}
