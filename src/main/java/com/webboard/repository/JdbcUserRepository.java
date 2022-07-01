package com.webboard.repository;

import com.webboard.model.Email;
import com.webboard.model.User;
import com.webboard.model.commons.Id;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static com.webboard.util.DateTimeUtils.dateTimeOf;
import static com.webboard.util.DateTimeUtils.timestampOf;

public class JdbcUserRepository implements UserRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcUserRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public User insert(User user) {
        KeyHolder keyHolder= new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO users(id,email,passwd,name,login_count,create_at) VALUES (null,?,?,?,?)",
                    new String[]{"seq"});
            ps.setString(1, user.getEmail().getAddress());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getName());
            ps.setInt(4, user.getLoginCount());
            ps.setTimestamp(5, timestampOf(user.getCreateAt()));
            return ps;
        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedId = key != null ? key.longValue() : -1;
        return new User.Builder(user)
                .id(generatedId)
                .build();
    }

    public void update(User user) {
        jdbcTemplate.update(
            "UPDATE users SET name=?, passwd=?, login_count=?, WHERE id=?",
                    user.getName(),
                    user.getPassword(),
                    user.getLoginCount(),
                    user.getId()
        );
    }

    public Optional<User> findById(Id<User, Long> userId) {
        List<User> rs = jdbcTemplate.query(
                "SELECT * FROM users WHERE id = ?",
                mapper,
                userId.value()
        );
        return Optional.ofNullable(rs.isEmpty() ? null : rs.get(0));
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
