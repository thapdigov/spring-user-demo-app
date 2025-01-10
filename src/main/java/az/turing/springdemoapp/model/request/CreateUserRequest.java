package az.turing.springdemoapp.model.request;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Builder
@RequiredArgsConstructor
@Data
public class CreateUserRequest {
    private String username;
    private String password;
    private String confirmPassword;
}
