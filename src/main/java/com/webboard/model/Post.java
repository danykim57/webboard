package com.webboard.model;

import com.webboard.model.commons.Id;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static com.google.common.base.Preconditions.checkArgument;
import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class Post {
    private final Long id;

    private String title;

    private String content;

    private final String writer;

    private final LocalDateTime createAt;

    public Post(Long id, String title, String content, String writer) {
        this(null, title, content, writer, null);
    }

    public Post(Long id, String title, String content, String writer, LocalDateTime createAt) {
        checkArgument(title.length() >= 0 && content.length() <= 50, "title length must be under 50 chars.");
        checkArgument(content.length() >= 0 && content.length() <= 500,
                "length of content must be under 500 chars.");
        this.id = id;
        this.title = title;
        this.content = content;
        this.writer = writer;
        this.createAt = defaultIfNull(createAt, now());
    }

    public Long getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return Objects.equals(id, post.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("title", title)
                .append("content", content)
                .append("writer", writer)
                .append("createAt", createAt)
                .toString();
    }

    static public class Builder {
        private Long id;

        private String title;

        private String content;

        private String writer;

        private LocalDateTime createAt;

        public Builder() {
        }

        public Builder(Post post) {
            this.id = post.id;
            this.title = post.title;
            this.content = post.content;
            this.writer = post.writer;
            this.createAt = post.createAt;
        }

        public Builder(Long id, String title, String content, String writer, LocalDateTime createAt) {
            this.id = id;
            this.title = title;
            this.content = content;
            this.writer = writer;
            this.createAt = createAt;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder content(String content) {
            this.content = content;
            return this;
        }

        public Builder writer(String writer) {
            this.writer = writer;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public Post build() {
            return new Post(id, title, content, writer, createAt);
        }
    }
}
