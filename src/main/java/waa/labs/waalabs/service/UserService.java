package waa.labs.waalabs.service;


import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.UserDto;

import java.util.List;

public interface UserService {
    List<UserDto> findAllUsers();
    UserDto findById(long id);
    void save(User user);
    List<PostDto> findUserPosts(long id);
    void addPostToUser(long userId, PostDto post);
    List<User> getUsersWithMoreThanOnePost();
}
