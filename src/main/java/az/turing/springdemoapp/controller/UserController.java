package az.turing.springdemoapp.controller;

import az.turing.springdemoapp.model.dto.UserDto;
import az.turing.springdemoapp.model.enums.UserStatus;
import az.turing.springdemoapp.model.request.CreateUserRequest;
import az.turing.springdemoapp.model.request.UpdateRequest;
import az.turing.springdemoapp.service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("users/api")
public class UserController {
    public final UserService userservice;

    @GetMapping("all")
    public ResponseEntity<Set<UserDto>> getAll() {
        return ResponseEntity.ok(userservice.findAll());
    }

    @PostMapping
    public ResponseEntity<UserDto> create(@Valid @RequestBody CreateUserRequest createUserRequest) {
        return ResponseEntity.status(HttpStatus.CREATED).body(userservice.create(createUserRequest));
    }

    @GetMapping("/{username}")
    public ResponseEntity<UserDto> getByUsername(@PathVariable String username) {
        return ResponseEntity.ok(userservice.findByUserName(username));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<UserDto> update(@PathVariable @RequestBody Integer id, UpdateRequest updateRequest) {
        return ResponseEntity.ok(userservice.update(id, updateRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updatePut(@PathVariable @RequestBody Integer id, UserStatus userStatus) {
        return ResponseEntity.ok(userservice.updatePut(id, userStatus));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable @RequestBody Integer id) {
        userservice.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
