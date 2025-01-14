package az.turing.springdemoapp.domain.entity;

import az.turing.springdemoapp.model.enums.UserStatus;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserEntity {
    private Integer Id;
    private String name;
    @ToString.Exclude
    private String password;
    private UserStatus userStatus;
}
