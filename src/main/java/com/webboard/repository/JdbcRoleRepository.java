package com.webboard.repository;

import com.webboard.model.Role;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.webboard.util.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcRoleRepository implements RoleRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcRoleRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Role> findByUid(Long id) {
        return jdbcTemplate.query("SELECT * FROM roles r JOIN  users_roles u ON r.id = u.role_id where u.user_id = ?", mapper, id);
    }

    static RowMapper<Role> mapper = (rs, rowNum) -> new Role.Builder()
            .id(rs.getLong("id"))
            .name(rs.getString("name"))
            .build();
}
