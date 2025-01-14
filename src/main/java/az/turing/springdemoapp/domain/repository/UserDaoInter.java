package az.turing.springdemoapp.domain.repository;

import az.turing.springdemoapp.domain.entity.UserEntity;

import java.util.Optional;
import java.util.Set;

public interface UserDaoInter {
    Set<UserEntity> findAll();

    boolean existsByUsername(String username);

    UserEntity save(UserEntity userEntity);

    Optional<UserEntity> findByUsername(String username);

    Optional<UserEntity> findById(Integer id);

    boolean existsById(Integer id);

    void deleteById(Integer id);


}
