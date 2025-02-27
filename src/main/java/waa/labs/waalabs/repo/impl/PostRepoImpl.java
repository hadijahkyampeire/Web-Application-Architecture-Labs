package waa.labs.waalabs.repo.impl;

import org.springframework.stereotype.Repository;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.repo.PostRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

//@Repository
public class PostRepoImpl {
//    private static List<Post> posts = new ArrayList<>();
//    private static long postId =  200;
//
//    static {
//        Post p1 = new Post(123, "Learning Spring", "Spring is a modular framework for Java used to build rest web services", "KH");
//        Post p2 = new Post(124, "Head First", "Great books for tech stuff about design patterns", "Oriely");
//        Post p3 = new Post(125, "Test title", "Test description", "Test author");
//        posts.add(p1);
//        posts.add(p2);
//        posts.add(p3);
//    }
//
//    public List<Post> findAll() {
//        return posts;
//    }

//    public Post findById(long id) {
//        return posts.stream().filter(post -> post.getId() == id).findFirst().orElse(null);
//    }
//
//    public void save(Post post) {
//        post.setId(postId);
//        postId++;
//        posts.add(post);
//    }
//
//    @Override
//    public void delete(long id) {
//        posts.remove(findById(id));
//    }
//
//    public void update(long id, Post post) {
//        Post postToUpdate = findById(id);
//        postToUpdate.setTitle(post.getTitle());
//        postToUpdate.setContent(post.getContent());
//        postToUpdate.setAuthor(post.getAuthor());
//    }
//
//    @Override
//    public List<Post> filterByAuthor(String authorName) {
//        return posts.stream()
//                .filter(post -> post.getAuthor().contains(authorName))
//                .collect(Collectors.toList());
//    }
}
