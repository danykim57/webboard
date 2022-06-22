package com.webboard.model;

import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

import java.time.LocalDateTime;

public class ConnectedUser {

    private final Long id;

    private final Email email;

    private final String name;

    private final LocalDateTime grantedAt;

    public ConnectedUser(Long id, Email email, String name, LocalDateTime grantedAt) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.grantedAt = grantedAt;
    }

    public String toString() {
        return new ToStringBuilder(this, ToStringStyle.SHORT_PREFIX_STYLE)
                .append("id", id)
                .append("email", email)
                .append("name", name)
                .append("grantedAt", grantedAt)
                .toString();
    }
}
