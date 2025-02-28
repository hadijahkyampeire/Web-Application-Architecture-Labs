package waa.labs.waalabs.service;

import waa.labs.waalabs.domain.Comment;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.ResponseDto;

import java.util.List;

public interface PostService {

    List<PostDto> findAllPosts();
    ResponseDto<PostDto> getPostById(long id);
    ResponseDto<PostDto> createPost(PostDto post);
    void updatePost(long id, PostDto post);
    void deletePost(long id);
    List<PostDto> filterPostByAuthor(String author);
    void addCommentToPost(long postId, Comment comment);
    List<Comment> getCommentsByPost(long postId);
    List<PostDto> getPostsByTitle(String title);
    List<PostDto> getPostsByAuthorAndTitle(String author, String title);
}
