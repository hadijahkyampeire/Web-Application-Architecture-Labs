package waa.labs.waalabs.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import waa.labs.waalabs.domain.Comment;
import waa.labs.waalabs.domain.Post;
import waa.labs.waalabs.dto.PostDto;
import waa.labs.waalabs.dto.ResponseDto;
import waa.labs.waalabs.helper.ListMapper;
import waa.labs.waalabs.repo.PostRepo;
import waa.labs.waalabs.service.PostService;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

@Service
public class PostServiceImpl implements PostService {

    private final PostRepo postRepo;

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    ListMapper listMapper;

    @Autowired
    public PostServiceImpl(PostRepo postRepo) {
        this.postRepo = postRepo;
    }

    @Override
   public List<PostDto> findAllPosts() {
       return listMapper.mapList(postRepo.findAll(), new PostDto());
   }


    @Override
   public ResponseDto<PostDto> getPostById(long id) {
       Post p = postRepo.findById(id).orElse(null);
       if (p == null) {
           return new ResponseDto<>("Post not found", HttpStatus.NOT_FOUND.value());
       }
       return new ResponseDto<>("Success", HttpStatus.OK.value(), modelMapper.map(p, PostDto.class));
   }
   @Override
    public ResponseDto<PostDto> createPost(PostDto post) {
        postRepo.save(modelMapper.map(post, Post.class));
        return new ResponseDto<PostDto>("Post created successfully", 201, post);
    }


    @Override
    public void updatePost(long id, PostDto post) {
       Post postToUpdate = postRepo.findById(id).orElseThrow(() -> new RuntimeException("Post not found"));
       postToUpdate.setId(id);
       postToUpdate.setTitle(post.getTitle());
       postToUpdate.setContent(post.getContent());
       postToUpdate.setAuthor(post.getAuthor());
       postRepo.save(postToUpdate);
    }

    @Override
    public void deletePost(long id) {
       postRepo.deleteById(id);
    }


    @Override
    @Transactional
    public void addCommentToPost(long postId, Comment comment) {
        Post post = postRepo.findById(postId).orElseThrow(() -> new RuntimeException("User not found with id: " + postId));
        post.addComment(comment);
//        postRepo.save(post);
    }

    @Override
    public List<Comment> getCommentsByPost(long postId) {
        Post post = postRepo.findById(postId).orElse(null);
        return post.getComments();
    }

    // For non persistent
//    @Override
//    public List<PostDto> findAllPosts() {
//        return listMapper.mapList(postRepo.findAll(), new PostDto());
//    }
//
//    @Override
//    public ResponseDto<PostDto> getPostById(long id) {
//        List<Post> allPosts = postRepo.findAll();
//        boolean postFound = allPosts.stream().anyMatch(post -> post.getId() == id);
//
//        if (!postFound) {
//            return new ResponseDto<PostDto>("Post not found", 404);
//        }
//        PostDto postDto = modelMapper.map(postRepo.findById(id), PostDto.class);
//
//        return new ResponseDto<PostDto>("Post retrieved successfully", 200, postDto);
//    }
//
//    @Override
//    public ResponseDto<Post> createPost(Post post) {
//        postRepo.save(post);
//        return new ResponseDto<Post>("Post created successfully", 201, post);
//    }
//
//    @Override
//    public void updatePost(long id, PostDto post) {
//        postRepo.update(id, modelMapper.map(post, Post.class));
//    }
//
//    @Override
//    public void deletePost(long id) {
//        postRepo.delete(id);
//    }
//

    // FILTERS
    @Override
    public List<PostDto> filterPostByAuthor(String author) {
        List<Post> filteredPosts = postRepo.findAll().stream()
                .filter(post -> post.getAuthor().toLowerCase().contains(author.toLowerCase()))
                .collect(Collectors.toList());

        return listMapper.mapList(filteredPosts, new PostDto());
    }

    @Override
    public List<PostDto> getPostsByTitle(String title) {
        return listMapper.mapList(postRepo.findPostsByTitle(title), new PostDto());
    }

    @Override
    public List<PostDto> getPostsByAuthorAndTitle(String author, String Title) {
        return listMapper.mapList(postRepo.findByAuthorAndTitle(author, Title), new PostDto());
    }
}
