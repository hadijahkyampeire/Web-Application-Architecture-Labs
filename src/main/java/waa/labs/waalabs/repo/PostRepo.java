package waa.labs.waalabs.repo;

import waa.labs.waalabs.domain.Post;

import java.util.List;

public interface PostRepo {
    List<Post> findAll();
    Post findById(long id);
    void save(Post post);
    void delete(long id);
    void update(long id, Post post);
    List<Post> filterByAuthor(String authorName);
}
