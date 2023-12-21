package com.application.blog.velvetvoices.repository;

import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.role.Role;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    public User findUserByUserEmail(String email);

    public User findUserByUsername(String username);

    public List<User> findUsersByUsernameContaining(String username);


    public List<User> findUsersByRoles(Role role);

}
