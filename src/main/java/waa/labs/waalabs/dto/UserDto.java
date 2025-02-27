package waa.labs.waalabs.dto;

import lombok.Data;
import waa.labs.waalabs.domain.Post;

import java.util.List;

@Data
public class UserDto {
    Long id;
    String name;

    List<Post> posts;

    public UserDto() {}
}
