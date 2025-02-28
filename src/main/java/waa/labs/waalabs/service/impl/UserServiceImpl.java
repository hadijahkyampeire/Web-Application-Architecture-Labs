package waa.labs.waalabs.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import waa.labs.waalabs.domain.Comment;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.dto.CommentDto;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.ResponseDto;
import waa.labs.waalabs.dto.UserDto;
import waa.labs.waalabs.helper.ListMapper;
import waa.labs.waalabs.repo.UserRepo;
import waa.labs.waalabs.service.UserService;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepo userRepo;

    @Autowired
    ListMapper listMapper;

    @Autowired
    ModelMapper modelMapper;

    @Override
    public List<UserDto> findAllUsers() {
        return listMapper.mapList(userRepo.findAll(), new UserDto());
    }

    @Override
    public ResponseDto<UserDto> findById(long id) {
        User user = userRepo.findById(id).orElse(null);
        if (user == null) {
            return new ResponseDto<>("User not found with id:" + id, HttpStatus.NOT_FOUND.value());
        }
        return new ResponseDto<>("User found", HttpStatus.OK.value(), modelMapper.map(user, UserDto.class));
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public void deleteById(long id) {
        userRepo.deleteById(id);
    }

    @Override
    public List<PostDto> findUserPosts(long id) {
        User user = userRepo.findById(id).orElse(null);
        return listMapper.mapList(user.getPosts(), new PostDto());
    }

    @Override
    public PostDto findUserPostById(long userId, long postId) {
        Post p = userRepo.findUserPostById(userId, postId);
        return modelMapper.map(p, PostDto.class);
    }

    //    @Transactional
    @Override
    public void addPostToUser(long userId, PostDto post) {
        // Step 1: Fetch the user
        User user = userRepo.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found with id: " + userId));

        Post postEntity = modelMapper.map(post, Post.class);
        // Step 2: Add the post to the user's collection
        user.addPost(postEntity);

        // Step 3: Save the user (posts will be cascaded)
        userRepo.save(user);
    }

    @Override
    public List<User> getUsersWithMoreThanOnePost() {
        return userRepo.findUsersWithMoreThanOnePost();
    };

    @Override
    public List<User> getUsersWithMoreThanNPosts(int n) {
        return userRepo.findUsersWithMoreThanNPost(n);
    }

    @Override
    public List<UserDto> getUserPostsByTitle(String title) {
        return listMapper.mapList(userRepo.findUserPostsByTitle(title), new UserDto());
    }

    @Override
    public CommentDto getUserPostCommentById(long userId, long postId, long commentId) {
        // Business Logic substituted with Query
//        User u = userRepo.findById(userId).orElse(null);
//        assert u != null;
//        Post p = u.getPosts().stream().filter(post -> post.getId() == postId).findFirst().orElse(null);
//        assert p != null;
//        Comment c = p.getComments().stream().filter(comment -> comment.getId() == commentId).findFirst().get();
        Comment c = userRepo.findUserPostCommentById(userId, postId, commentId);
        return modelMapper.map(c, CommentDto.class);
    }
}
