package com.webboard.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Objects;

import static java.time.LocalDateTime.now;
import static org.apache.commons.lang3.ObjectUtils.defaultIfNull;

public class User {
    private Long id;

    private String name;
    private String email;

    private String password;
    private Collection<String> roles;

    private int loginCount;

    private LocalDateTime createAt;

    public User(String name, String email, String password) {
        this(null, name, email, password, List.of(), 0, null);
    }

    public void encryptPassword(PasswordEncoder passwordEncoder) {
        password = passwordEncoder.encode(password);
    }

    public User() {
    }

    public User(Long id, String name, String email, String password, Collection<String> roles, int loginCount, LocalDateTime createAt) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.roles = roles;
        this.loginCount = loginCount;
        this.createAt = defaultIfNull(createAt, now());
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public int getLoginCount() {
        return loginCount;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setroles(Collection<String> roles) {
        this.roles = roles;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return Objects.equals(id, user.id);
    }
    //id 인식번호를 통한 해쉬 비교
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
                .append("createAt", createAt)
                .toString();
    }

    public <T> void setRoles(Collection<String> roles_user) {
        this.roles = roles_user;
    }

    public Collection<String> getRoles() {
        return this.roles;
    }

    static public class Builder {
        private Long id;
        private String name;
        private String email;
        private String password;
        private Collection<String> roles;
        private int loginCount;
        private LocalDateTime createAt;

        public Builder() {
        }

        public Builder(User user) {
            this.id = user.id;
            this.name = user.name;
            this.email = user.email;
            this.password = user.password;
            this.roles = user.roles;
            this.loginCount = user.loginCount;
            this.createAt = user.createAt;
        }

        public Builder(String principal, String credentials) {
            this.email = principal;
            this.password = credentials;
        }

        public Builder id(Long id) {
            this.id = id;
            return this;
        }


        public Builder name(String name) {
            this.name = name;
            return this;
        }

        public Builder email(String email) {
            this.email = email;
            return this;
        }

        public Builder password(String password) {
            this.password = password;
            return this;
        }

        public Builder roles(Collection<String> roles) {
            this.roles = roles;
            return this;
        }

        public Builder loginCount(int loginCount) {
            this.loginCount = loginCount;
            return this;
        }

        public Builder createAt(LocalDateTime createAt) {
            this.createAt = createAt;
            return this;
        }

        public User build() {
            return new User(id, name, email, password, roles, loginCount, createAt);
        }
    }
}
