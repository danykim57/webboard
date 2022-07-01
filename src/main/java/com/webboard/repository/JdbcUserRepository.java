package com.webboard.repository;

import com.webboard.controller.UserDTO;
import com.webboard.model.Email;
import com.webboard.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

import static com.webboard.util.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<User> getUsers() {
        return jdbcTemplate.query("SELECT * FROM users", mapper);
    }

    public Optional<User> getUser(long id) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE seq = ?", mapper, id);
        return Optional.ofNullable(user);
    }

    public long join(UserDTO userDTO) {
        return jdbcTemplate.update("INSERT INTO users (email, passwd) VALUES(?,?)", userDTO.getEmail(), userDTO.getPassword(), mapper);
    }

    static RowMapper<User> mapper = (rs, rowNum) -> new User.Builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .email(new Email(rs.getString("meail")))
            .password(rs.getString("passwd"))
            .loginCount(rs.getInt("login_count"))
            .createAt(dateTimeOf(rs.getTimestamp("create_at")))
            .build();
}
