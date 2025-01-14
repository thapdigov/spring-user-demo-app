package az.turing.springdemoapp.domain.repository;

import az.turing.springdemoapp.domain.entity.UserEntity;
import az.turing.springdemoapp.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Repository
public class UserDataBaseRepository implements UserDaoInter {
    private final JdbcTemplate jdbcTemplate;

    @Override
    public Set<UserEntity> findAll() {
        String query = "select *from users";
        return (Set<UserEntity>) jdbcTemplate.query(query, new UserRowMapper());
    }

    @Override
    public boolean existsByUsername(String username) {
        return findAll().stream().anyMatch(userEntity -> userEntity.getName().equals(username));
    }

    public UserEntity save(UserEntity userEntity) {
        String query = "insert into users (name,password,userstatus) values(?,?,?)";
        jdbcTemplate.update(query, userEntity.getName(), userEntity.getPassword(), userEntity.getUserStatus());
        return userEntity;
    }

    @Override
    public Optional<UserEntity> findByUsername(String username) {
        return Optional.of(findAll().stream().filter(userEntity -> userEntity.getName().equals(username))
                .findFirst()).orElseThrow(() -> new NotFoundException("User with " + username + "not found"));
    }

    @Override
    public Optional<UserEntity> findById(Integer id) {
        return Optional.of(findAll().stream().filter(userEntity -> userEntity.getId().equals(id))
                .findFirst()).orElseThrow(() -> new NotFoundException("User with " + id + "not found"));
    }

    @Override
    public boolean existsById(Integer id) {
        return findAll().stream().anyMatch(userEntity -> userEntity.getId().equals(id));
    }

    public void deleteById(Integer userId) {
        String query = "delete from users where userId=?";
        jdbcTemplate.update(query, userId);
    }
}

