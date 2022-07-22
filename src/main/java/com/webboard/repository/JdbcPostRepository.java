package com.webboard.repository;

import com.webboard.model.Post;
import com.webboard.model.User;
import com.webboard.model.commons.Id;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;
import java.util.Optional;

import static com.webboard.util.DateTimeUtils.dateTimeOf;
import static java.util.Optional.ofNullable;

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

    @Override
    public void update(Post post) {
        jdbcTemplate.update(
                "UPDATE posts SET contents=?, writer=? WHERE id=?",
                post.getContent(),
                post.getWriter(),
                post.getId()
        );
    }

    @Override
    public Post insert(Post post) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        jdbcTemplate.update(conn -> {
            PreparedStatement ps = conn.prepareStatement("INSERT INTO posts(id, uid, title, content, writer) VALUES (null,?,?,?,?)", new String[]{"id"});
            ps.setLong(1, post.getUid().value());
            ps.setString(2, post.getTitle());
            ps.setString(3, post.getContent());
            ps.setString(4, post.getWriter());
            return ps;

        }, keyHolder);

        Number key = keyHolder.getKey();
        long generatedId = key != null ? key.longValue() : -1;
        return new Post.Builder(post)
                .id(generatedId)
                .build();
    }

    @Override
    public Optional<Post> findByid(Id<Post, Long> postId) {
        List<Post> results = jdbcTemplate.query(
                "SELECT p.*, u.email FROM psots p JOIN users u ON p.uid=u.id WHERE p.id=?",
                mapper,
                postId.value()
        );
        return ofNullable(results.isEmpty() ? null : results.get(0));
    }

    static RowMapper<Post> mapper = (rs, rowNum) -> new Post.Builder()
            .id(rs.getLong("id"))
            .uid(Id.of(User.class, rs.getLong("uid")))
            .title(rs.getString("title"))
            .content(rs.getString("content"))
            .writer(rs.getString("writer"))
            .createAt(dateTimeOf(rs.getTimestamp("create_at")))
            .build();
}
