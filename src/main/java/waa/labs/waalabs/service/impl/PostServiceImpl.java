package waa.labs.waalabs.service.impl;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.ResponseDto;
import waa.labs.waalabs.helper.ListMapper;
import waa.labs.waalabs.repo.PostRepo;
import waa.labs.waalabs.service.PostService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Override
    public List<PostDto> findAllPosts() {
        return listMapper.mapList(postRepo.findAll(), new PostDto());
    }

    @Override
    public ResponseDto<PostDto> getPostById(long id) {
        List<Post> allPosts = postRepo.findAll();
        boolean postFound = allPosts.stream().anyMatch(post -> post.getId() == id);

        if (!postFound) {
            return new ResponseDto<PostDto>("Post not found", 404);
        }
        PostDto postDto = modelMapper.map(postRepo.findById(id), PostDto.class);

        return new ResponseDto<PostDto>("Post retrieved successfully", 200, postDto);
    }

    @Override
    public ResponseDto<Post> createPost(Post post) {
        postRepo.save(post);
        return new ResponseDto<Post>("Post created successfully", 201, post);
    }

    @Override
    public void updatePost(long id, PostDto post) {
        postRepo.update(id, modelMapper.map(post, Post.class));
    }

    @Override
    public void deletePost(long id) {
        postRepo.delete(id);
    }

    @Override
    public List<PostDto> filterPostByAuthor(String author) {
        return listMapper.mapList(postRepo.filterByAuthor(author), new PostDto());
    }
}
