package az.turing.springdemoapp.domain.repository;

import az.turing.springdemoapp.domain.entity.UserEntity;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class UserRepository implements UserDaoInter {
    public final AtomicInteger atomicInteger = new AtomicInteger();
    private final static Set<UserEntity> USERS = new HashSet<>();

    public Set<UserEntity> findAll() {
        return USERS;
    }

    public boolean existsByUsername(String username) {
        return USERS.stream().anyMatch(userEntity -> userEntity.getName().equals(username));
    }

    public UserEntity save(UserEntity userEntity) {
        if (userEntity.getId() == null) {
            userEntity.setId(atomicInteger.incrementAndGet());
            USERS.add(userEntity);
            return userEntity;
        } else {
            return userEntity;
        }
    }

    public Optional<UserEntity> findByUsername(String username) {
        return USERS.stream()
                .filter(userEntity -> userEntity.getName().equals(username))
                .findFirst();
    }

    public Optional<UserEntity> findById(Integer id) {
        return USERS.stream()
                .filter(userEntity -> userEntity.getId().equals(id))
                .findFirst();
    }

    public boolean existsById(Integer id) {
        return USERS.stream().anyMatch(userentity -> userentity.getId().equals(id));
    }

    public void deleteById(Integer id) {
        USERS.stream().filter(userEntity -> userEntity.getId().equals(id)).findFirst().ifPresent(USERS::remove);
    }
}
