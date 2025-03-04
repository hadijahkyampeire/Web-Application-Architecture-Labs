package waa.labs.waalabs.dto.response;

import lombok.Builder;
import lombok.Data;
import waa.labs.waalabs.domain.Role;

import java.util.List;

@Data
@Builder
public class SignUpResponse {
    private String message;
    private String email;
    private String firstname;
    private String lastname;
    private List<Role> roles;
}
