package az.turing.springdemoapp.mapper;

import az.turing.springdemoapp.domain.entity.UserEntity;
import az.turing.springdemoapp.model.dto.UserDto;
import az.turing.springdemoapp.model.request.CreateUserRequest;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {
    public UserDto toDto(UserEntity entity) {
        return UserDto.builder()
                .Id(entity.getId())
                .name(entity.getName())
                .password(entity.getPassword())
                .userStatus(entity.getUserStatus())
                .build();
    }

    public UserEntity toEnt(UserDto userDto) {
        return UserEntity.builder()
                .Id(userDto.getId())
                .name(userDto.getName())
                .password(userDto.getPassword())
                .userStatus(userDto.getUserStatus())
                .build();
    }

    public UserEntity toEnt(CreateUserRequest request) {
        return UserEntity.builder()
                .name(request.getUsername())
                .password(request.getPassword())
                .build();
    }
}
