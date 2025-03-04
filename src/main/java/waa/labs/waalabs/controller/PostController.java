package waa.labs.waalabs.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import waa.labs.waalabs.domain.Comment;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.ResponseDto;
import waa.labs.waalabs.service.PostService;

import java.util.List;


@RestController
@RequestMapping("/api/v1/posts")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class PostController {
    private final PostService postService;

    @Autowired
    private ModelMapper modelMapper;

    @Autowired // OR can be ignored
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostDto> getAllByAuthorAndOrTitle(
            @RequestParam(value="author", required = false) String author,
            @RequestParam(value="title", required = false) String title) {
        if (author != null && title != null) {
            // Fetch posts by both author and title
            return postService.getPostsByAuthorAndTitle(author, title);
        } else if (author != null) {
            // Fetch posts by author
            return postService.filterPostByAuthor(author);
        } else if (title != null) {
            // Fetch posts by title
            return postService.getPostsByTitle(title);
        } else {
            // Fetch All
            return postService.findAllPosts();
        }
    }


    @GetMapping("/{id}")
    public ResponseDto<PostDto> getPostById(@PathVariable long id) {
        return postService.getPostById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ResponseDto<PostDto>> savePost(@RequestBody PostDto post) {
        ResponseDto<PostDto> res = postService.createPost(post);
        return ResponseEntity.status(res.getStatusCode()).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updatePost(@PathVariable long id, @RequestBody PostDto post) {
        postService.updatePost(id, post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable long id) {
        postService.deletePost(id);
    }

    // COMMENTS
    // Add comments to a post
    @PostMapping("/{id}/comments")
    @ResponseStatus(HttpStatus.CREATED)
    public void addComment(@PathVariable long id, @RequestBody Comment comment) {
        postService.addCommentToPost(id, comment);
    }

    @GetMapping("/{id}/comments")
    public List<Comment> getComments(@PathVariable long id) {
        return postService.getCommentsByPost(id);
    }
}
