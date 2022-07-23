package com.webboard.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Objects;

public class Role {

    private Long id;

    private String name;

    Role() {
    }

    Role(String name) {
        this(null, name);
    }

    Role(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Role role = (Role) o;
        return Objects.equals(id, role.id);
    }

    //id 인식번호를 통한 해쉬 비교
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("name", name)
                .toString();
    }

    static public class Builder {

        private Long id;

        private String name;

        public Builder() {
        }

        public Builder(Role role) {
            this.id = role.id;
            this.name = role.name;
        }

        public Role.Builder id(Long id) {
            this.id = id;
            return this;
        }


        public Role.Builder name(String name) {
            this.name = name;
            return this;
        }

        public Role build() {
            return new Role(id, name);
        }

    }
}
