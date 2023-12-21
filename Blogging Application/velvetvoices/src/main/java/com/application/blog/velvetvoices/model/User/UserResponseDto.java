package com.application.blog.velvetvoices.model.User;

import com.application.blog.velvetvoices.model.role.Role;
import com.application.blog.velvetvoices.model.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import java.util.List;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserResponseDto {

    private long userId;

    private String userEmail;

    private String firstName;

    private String lastName;

    @JsonIgnore
    private String password;

    private String about;

    private String username;

    @JsonIgnore
    private List<Post> posts;

    @JsonIgnore
    private List<Role> roles;
}
