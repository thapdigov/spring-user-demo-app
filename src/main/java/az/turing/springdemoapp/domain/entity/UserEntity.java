package az.turing.springdemoapp.domain.entity;

import az.turing.springdemoapp.model.enums.UserStatus;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Builder
@RequiredArgsConstructor
public class UserEntity {
    private Integer Id;
    private String name;
    private String password;
    private UserStatus userStatus;
}
