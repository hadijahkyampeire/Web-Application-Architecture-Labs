package waa.labs.waalabs.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import waa.labs.waalabs.aspect.annotation.ExecutionTime;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.dto.CommentDto;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.ResponseDto;
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
    public List<UserDto> getAllUsers(@RequestParam(required = false, value = "title") String postTitle) {
        return postTitle != null ? userService.getUserPostsByTitle(postTitle) : userService.findAllUsers();
    }

    @ExecutionTime
    @GetMapping("/{id}")
    public ResponseDto<UserDto> getUserById(@PathVariable long id) {
        return userService.findById(id);
    }

    @PostMapping
    public void addUser(@RequestBody User user) {
        userService.save(user);
    }

    @DeleteMapping("/{id}")
    public void deleteUserById(@PathVariable long id) {
        userService.deleteById(id);
    }

    @GetMapping("/{id}/posts")
    public List<PostDto> getPostsForUser(@PathVariable long id) {
        return userService.findUserPosts(id);
    }

    @GetMapping("/{id}/posts/{postId}")
    public PostDto getUserPostById(@PathVariable long id, @PathVariable long postId) {
        return userService.findUserPostById(id, postId);
    }

    @PostMapping("/{id}/posts")
    public void addPostToUser(@PathVariable long id, @RequestBody PostDto post) {
        userService.addPostToUser(id, post);
    }

    @GetMapping("/filter-by-posts")
    public List<User> filterByNumberOfPosts(@RequestParam(required = false, defaultValue = "0") int number) {
        return number != 0
                ? userService.getUsersWithMoreThanNPosts(number)
                : userService.getUsersWithMoreThanOnePost();
    }

    @GetMapping("/{userId}/posts/{postId}/comments/{commentId}")
    public CommentDto getCommentById(@PathVariable long userId, @PathVariable long postId, @PathVariable long commentId) {
        return userService.getUserPostCommentById(userId, postId, commentId);
    }

}
