package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.model.User.UserResponseDto;
import com.application.blog.velvetvoices.model.role.RoleRequestDto;
import com.application.blog.velvetvoices.model.role.RoleResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface RoleService {

    RoleResponseDto  getRole(Long roleId);

    RoleResponseDto  getRoleByName(String roleName);

    RoleResponseDto  createRole(RoleRequestDto roleRequestDto);

    RoleResponseDto  updateRole(RoleRequestDto roleRequestDto, Long roleId);

    String  deleteRole(Long roleId);

    String  deleteRoleByName(String roleName);

    List<RoleResponseDto> getAllRoles();

    List<RoleResponseDto> getRolesByUser(Long userId);

    List<UserResponseDto> getUsersByRoleId(Long roleId);

    UserResponseDto addRoleToUser(Long userId, Long roleId);

    List<RoleResponseDto> saveAllRoles(List<RoleRequestDto> roleRequestDtos);
}
