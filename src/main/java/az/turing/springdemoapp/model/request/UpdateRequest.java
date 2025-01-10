package az.turing.springdemoapp.model.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class UpdateRequest {
    @Email
    private String username;
    @Pattern(regexp = "")
    private String password;
}
