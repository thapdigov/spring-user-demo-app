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
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class UserService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;

    public Set<UserDto> findAll() {
        log.info("Fetching all users from the database....");
        return userRepository.findAll().stream().map(userMapper::toDto).collect(Collectors.toSet());
    }

    public UserDto create(CreateUserRequest createUserRequest) {
        log.info("Creating a new user with username: {}", createUserRequest.getUsername());
        if (!createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
            log.error("Password and ConfirmPassword don't match for the username: {}", createUserRequest.getUsername());
            throw new IllegalArgumentException("Password don't match!");
        }
        existsByUserName(createUserRequest.getUsername());
        UserEntity savedUserEntity = userRepository.save(userMapper.toEnt(createUserRequest));
        log.info("User created: {}", savedUserEntity);
        return userMapper.toDto(savedUserEntity);
    }

    public UserDto findByUserName(String username) {
        log.info("Searching by username: {}", username);
        return userRepository.findByUsername(username).map(userMapper::toDto)
                .orElseThrow(() -> {
                    log.error("Username not found: {}", username);
                    return new NotFoundException("User  with username: " + username + "not found");
                });

    }

    public UserDto update(Integer id, UpdateRequest updateRequest) {
        existsByUserName(updateRequest.getUsername());
        UserEntity entity = userRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("User not found with ID: {}", id);
                    return new NotFoundException("User with id: " + id + "not found!");
                });
        entity.setName(updateRequest.getUsername());
        entity.setPassword(updateRequest.getPassword());
        UserEntity updatedEntity = userRepository.save(entity);
        log.info("User with ID: {} updated succesfully {}", id, updatedEntity);
        return userMapper.toDto(updatedEntity);
    }

    public UserDto updatePut(Integer id, UserStatus userStatus) {
        return userRepository.findById(id).map(userentity -> {
            userentity.setUserStatus(userStatus);
            UserEntity updatedEntity = userRepository.save(userentity);
            return userMapper.toDto(updatedEntity);
        }).orElseThrow(() -> {
            log.info("User with id: {} not found", id);
            return new NotFoundException("User with id: " + id + "not found!");
        });
    }

    public void deleteById(Integer id) {
        if (!userRepository.existsById(id)) {
            log.warn("User not found with Id: {}", id);
        }
        log.info("User deleted with id: {}", id);
        userRepository.deleteById(id);
    }

    private void existsByUserName(String username) {
        if (userRepository.existsByUsername(username)) {
            log.error("User with username: {} already exists!", username);
            throw new AlreadyException("User with username: " + username + "already exists");
        }
    }

    public UserDto findById(Integer id) {
        return userRepository.findById(id).map(userMapper::toDto).orElseThrow(() -> {
            log.error("User with id: {} not found!", id);
            return new NotFoundException("User with id: " + id + " not found!");
        });
    }
}
