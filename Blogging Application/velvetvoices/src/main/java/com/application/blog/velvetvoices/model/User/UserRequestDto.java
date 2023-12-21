package com.application.blog.velvetvoices.model.User;

import com.application.blog.velvetvoices.model.role.Role;
import com.application.blog.velvetvoices.model.post.Post;
import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserRequestDto {

//    @NotBlank(message = "User Id cannot be blank")
//    @NotNull(message = "User Id cannot be null")
//    private long userId;

    @NotBlank(message = "User Email cannot be blank")
    @NotNull(message = "User Email cannot be null")
    @Email
    private String userEmail;

    @NotBlank(message = "User Name cannot be blank")
    @NotNull(message = "User Name cannot be null")
    @Column(unique = true) // unique constraint on the column.
    private String username;

    @NotBlank(message = "First Name cannot be blank")
    @NotNull(message = "First Name cannot be null")
    private String firstName;

    @NotBlank(message = "Last Name cannot be blank")
    @NotNull(message = "Last Name cannot be null")
    private String lastName;

    @NotBlank(message = "Password cannot be blank")
    @NotNull(message = "Password cannot be null")
    private String password;

    @NotBlank(message = "About cannot be blank")
    @NotNull(message = "About cannot be null")
    @Column(length = 500) // max length of the column is 500 characters.
    private String about;

    private List<Post> posts = new ArrayList<>();

    private List<Role> roles = new ArrayList<>();

}
