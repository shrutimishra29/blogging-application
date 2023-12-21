package com.application.blog.velvetvoices.repository;

import com.application.blog.velvetvoices.model.role.Role;
import com.application.blog.velvetvoices.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findRoleByRoleName(String name);

    List<Role> findRolesByUsers(User user);
}
