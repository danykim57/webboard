package com.webboard.model;

import lombok.Getter;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

@Getter
public class User {
    private final Long id;

    private String name;

    private final Email email;

    private String password;

    private int loginCount;

    private LocalDateTime lastLoginAt;

    private final LocalDateTime createAt;

    public User(String name, Email email, String password) {
        this(null, name, email, password, 0, null, null);
    }

    public User(Long id, String name, Email email, String password, int loginCount, LocalDateTime lastLoginAt, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.loginCount = loginCount;
        this.lastLoginAt = lastLoginAt;
        this.createAt = defaultIfNull(createAt, now());
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", name)
                .append("email", email)
                .append("password", "[PROTECTED]")
                .append("loginCount", loginCount)
                .append("lastLoginAt", lastLoginAt)
                .append("createAt", createAt)
                .toString();
    }

    static public class Builder {
        private Long id;
        private String name;
        private Email email;
        private String password;
        private int loginCount;
        private LocalDateTime lastLoginAt;
        private LocalDateTime createAt;

        public Builder() {
        }

        public Builder(User user) {
            this.id = user.id;
            this.email = user.email;
            this.password = user.password;
            this.name = user.name;
            this.loginCount = user.loginCount;
            this.lastLoginAt = user.lastLoginAt;
            this.createAt = user.createAt;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(Email email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder loginCount(int loginCount) {
            this.loginCount = loginCount;
            return this;
        }

        public Builder lastLoginAt(LocalDateTime lastLoginAt) {
            this.lastLoginAt = lastLoginAt;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public User build() {
            return new User(id, name, email, password, loginCount, lastLoginAt, createAt);
        }
    }
}
