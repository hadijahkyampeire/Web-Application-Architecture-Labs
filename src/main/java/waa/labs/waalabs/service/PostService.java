package waa.labs.waalabs.service;

import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.ResponseDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPosts();
    PostDto getPostById(long id);
    ResponseDto<PostDto> createPost(PostDto post);
    void updatePost(long id, PostDto post);
    void deletePost(long id);
    List<PostDto> filterPostByAuthor(String author);
}
