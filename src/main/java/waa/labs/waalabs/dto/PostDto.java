package waa.labs.waalabs.dto;
import lombok.Data;

@Data
public class PostDto {
    long id;
    String title;
    String content;
    String author;

    public PostDto() {

    }
}
