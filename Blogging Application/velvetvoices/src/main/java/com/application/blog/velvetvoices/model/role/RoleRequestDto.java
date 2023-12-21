package com.application.blog.velvetvoices.model.role;

import com.application.blog.velvetvoices.model.User.User;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleRequestDto {

    private Long roleId;

    @NotNull(message = "Role name cannot be null")
    @NotBlank(message = "Role name cannot be blank")
    @Column(unique = true) // unique constraint on the column.
    private String roleName;

    @NotNull(message = "Role description cannot be null")
    @NotBlank(message = "Role description cannot be blank")
    @Column(length = 500) // max length of the column is 500 characters.
    private String roleDescription;

    private List<User> users;
}
