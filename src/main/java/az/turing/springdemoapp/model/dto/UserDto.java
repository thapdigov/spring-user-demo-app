package az.turing.springdemoapp.model.dto;

import az.turing.springdemoapp.model.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserDto {
    private Integer Id;
    private String name;
    private String password;
    private UserStatus userStatus;
}
