package waa.labs.waalabs.dto;
import lombok.Data;
import waa.labs.waalabs.domain.Comment;

import java.util.List;

@Data
public class PostDto {
    long id;
    String title;
    String content;
    String author;

    List<Comment> comments;

    public PostDto() {

    }
}
