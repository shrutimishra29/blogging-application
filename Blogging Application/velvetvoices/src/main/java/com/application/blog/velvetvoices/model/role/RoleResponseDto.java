package com.application.blog.velvetvoices.model.role;

import com.application.blog.velvetvoices.model.User.User;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.util.List;

@Data
@Builder
public class RoleResponseDto {

    private Long roleId;

    private String roleName;

    private String roleDescription;

    @JsonIgnore
    private List<User> users;
}
