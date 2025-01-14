package az.turing.springdemoapp.domain.repository;

import az.turing.springdemoapp.domain.entity.UserEntity;
import az.turing.springdemoapp.model.enums.UserStatus;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

class UserRowMapper implements RowMapper<UserEntity> {

    @Override
    public UserEntity mapRow(ResultSet resultSet, int i) throws SQLException {
        return UserEntity.builder()
                .Id(resultSet.getInt("id"))
                .name(resultSet.getString("name"))
                .password(resultSet.getString("password"))
                .userStatus(UserStatus.valueOf(resultSet.getString("userstatus")))
                .build();
    }
}
