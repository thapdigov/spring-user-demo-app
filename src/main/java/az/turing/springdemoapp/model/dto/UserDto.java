package az.turing.springdemoapp.model.dto;

import az.turing.springdemoapp.model.enums.UserStatus;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {
    private Integer Id;
    private String name;
    private String password;
    private UserStatus userStatus;
}
