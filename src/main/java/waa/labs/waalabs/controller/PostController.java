package waa.labs.waalabs.controller;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
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
    public PostController(PostService postService, ModelMapper modelMapper) {
        this.postService = postService;
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostDto> getAllByAuthor(@RequestParam(value="author", required = false) String author) {
        return author == null ? postService.findAllPosts() : postService.filterPostByAuthor(author);
    }

    @GetMapping("/{id}")
    public ResponseDto<PostDto> getPostById(@PathVariable int id) {
        return postService.getPostById(id);
    }

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping
    public ResponseEntity<ResponseDto<Post>> savePost(@RequestBody Post post) {
        ResponseDto<Post> res = postService.createPost(post);
        return ResponseEntity.status(res.getStatusCode()).body(res);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/{id}")
    public void updatePost(@PathVariable Long id, @RequestBody PostDto post) {
        postService.updatePost(id, post);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{id}")
    public void deletePost(@PathVariable Long id) {
        postService.deletePost(id);
    }


}
