package waa.labs.waalabs.service.impl;

import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.domain.User;
import waa.labs.waalabs.dto.PostDto;
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
    public UserDto findById(long id) {
        User user = userRepo.findById(id).orElse(null);
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public void save(User user) {
        userRepo.save(user);
    }

    @Override
    public List<PostDto> findUserPosts(long id) {
        User user = userRepo.findById(id).orElse(null);
        return listMapper.mapList(user.getPosts(), new PostDto());
    }

    @Transactional
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
}
