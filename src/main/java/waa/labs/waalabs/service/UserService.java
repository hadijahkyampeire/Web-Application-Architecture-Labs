package waa.labs.waalabs.service;


import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.dto.CommentDto;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.ResponseDto;
import waa.labs.waalabs.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    ResponseDto<UserDto> findById(long id);
    void save(User user);
    void deleteById(long id);
    List<PostDto> findUserPosts(long id);
    PostDto findUserPostById(long userId, long id);
    void addPostToUser(long userId, PostDto post);
    List<User> getUsersWithMoreThanOnePost();
    List<User> getUsersWithMoreThanNPosts(int n);
    List<UserDto> getUserPostsByTitle(String title);
    CommentDto getUserPostCommentById(long userId, long postId, long commentId);
}
