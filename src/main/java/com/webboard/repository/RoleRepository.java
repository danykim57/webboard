package com.webboard.repository;

import com.webboard.model.Role;

import java.util.List;

public interface RoleRepository {
    List<Role> findByUid(Long id);
}
