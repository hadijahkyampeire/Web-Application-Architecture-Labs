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
    @Autowired
    PostService postService;


    @ResponseStatus(HttpStatus.OK)
    @GetMapping
    public List<PostDto> getAllByAuthor(@RequestParam(value="author", required = false) String author) {
        return author == null ? postService.findAllPosts() : postService.filterPostByAuthor(author);
    }

    @GetMapping("/{id}")
    public PostDto getPostById(@PathVariable long id) {
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


}
