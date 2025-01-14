package az.turing.springdemoapp.model.request;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
@Getter
@Setter
public class CreateUserRequest {
    private String username;
    private String password;
    private String confirmPassword;
}
