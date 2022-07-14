package com.webboard.repository;

import com.webboard.model.Post;
import com.webboard.model.User;
import com.webboard.model.Writer;
import com.webboard.model.commons.Id;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.webboard.util.DateTimeUtils.dateTimeOf;

@Repository
public class JdbcPostRepository implements PostRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcPostRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Post> getPostList() {
        return jdbcTemplate.query(
                "SELECT * FROM posts p ORDER BY p.id DESC",
                mapper
        );
    }

    static RowMapper<Post> mapper = (rs, rowNum) -> new Post.Builder()
            .id(rs.getLong("id"))
//            .uid(Id.of(User.class, rs.getLong("uid")))
            .title(rs.getString("title"))
            .content(rs.getString("content"))
            .writer(new Writer(rs.getString("writer")))
            .createAt(dateTimeOf(rs.getTimestamp("create_at")))
            .build();
}
