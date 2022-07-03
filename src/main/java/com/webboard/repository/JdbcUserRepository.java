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

    public User findByEmail(String email) {
        List<User> rs = jdbcTemplate.query("SELECT * FROM users where email=?", mapper, email);
        return rs.isEmpty() ? null : rs.get(0);
    }

    public Optional<User> getUser(long id) {
        User user = jdbcTemplate.queryForObject("SELECT * FROM users WHERE seq = ?", mapper, id);
        return Optional.ofNullable(user);
    }

    public User save(User user) {
        jdbcTemplate.update("INSERT INTO users (name, email, password) VALUES(?,?,?)", user.getName(), user.getEmail(), user.getPassword(), mapper);
        return findByEmail(user.getEmail());
    }

    static RowMapper<User> mapper = (rs, rowNum) -> new User.Builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .email(rs.getString("email"))
            .password(rs.getString("password"))
            .loginCount(rs.getInt("login_count"))
            .createAt(dateTimeOf(rs.getTimestamp("create_at")))
            .build();
}
