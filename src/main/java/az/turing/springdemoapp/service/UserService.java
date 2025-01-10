package az.turing.springdemoapp.service;

import az.turing.springdemoapp.domain.entity.UserEntity;
import az.turing.springdemoapp.domain.repository.UserRepository;
import az.turing.springdemoapp.exception.AlreadyException;
import az.turing.springdemoapp.exception.NotFoundException;
import az.turing.springdemoapp.mapper.UserMapper;
import az.turing.springdemoapp.model.dto.UserDto;
import az.turing.springdemoapp.model.enums.UserStatus;
import az.turing.springdemoapp.model.request.CreateUserRequest;
import az.turing.springdemoapp.model.request.UpdateRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public Set<UserDto> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toSet());
    }

    public UserDto create(CreateUserRequest createUserRequest) {
        if (!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
            throw new IllegalArgumentException("Password don't match!");
        }
        existsByUserName(createUserRequest.getUsername());
        UserEntity savedUserEntity = userRepository.save(userMapper.toEnt(createUserRequest));
        return userMapper.toDto(savedUserEntity);
    }

    public UserDto findByUserName(String username) {
        return userRepository.findByUsername(username).map(userMapper::toDto)
                .orElseThrow(() -> new NotFoundException("User  with username: " + username + "not found"));

    }

    public UserDto update(Integer id, UpdateRequest updateRequest) {
        existsByUserName(updateRequest.getUsername());
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> new NotFoundException("User with id: " + id + "not found!"));
        entity.setName(updateRequest.getUsername());
        entity.setPassword(updateRequest.getPassword());
        UserEntity updatedEntity = userRepository.save(entity);
        return userMapper.toDto(updatedEntity);

    }

    public UserDto updatePut(Integer id, UserStatus userStatus) {
        return userRepository.findById(id).map(userentity -> {
            userentity.setUserStatus(userStatus);
            UserEntity updatedEntity = userRepository.save(userentity);
            return userMapper.toDto(updatedEntity);
        }).orElseThrow(() -> new NotFoundException("User with id: " + id + "not foound"));
    }

    public void deleteById(Integer id) {
        userRepository.deleteById(id);
    }

    private void existsByUserName(String username) {
        if (userRepository.existsByUsername(username)) {
            throw new AlreadyException("User with username: " + username + "already exists");
        }
    }
}
