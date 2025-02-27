package waa.labs.waalabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.UserDto;
import waa.labs.waalabs.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class UserController {
    @Autowired
    UserService userService;

    @GetMapping
    public List<UserDto> getAllUsers() {
        return userService.findAllUsers();
    }

    @GetMapping("/{id}")
    public UserDto getUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }

    @GetMapping("/{id}/posts")
    public List<PostDto> getPostsByUser(@PathVariable long id) {
        return userService.findUserPosts(id);
    }

    @PostMapping("/{id}/posts")
    public void addPostToUser(@PathVariable long id, @RequestBody PostDto post) {
        userService.addPostToUser(id, post);
    }

    @GetMapping("/with-more-than-one-posts")
    public List<User> getUsersWithMoreThanOnePost() {
        return userService.getUsersWithMoreThanOnePost();
    }

}
