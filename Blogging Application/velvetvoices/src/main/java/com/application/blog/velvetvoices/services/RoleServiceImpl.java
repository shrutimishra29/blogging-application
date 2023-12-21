package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.exceptions.ResourceConflictException;
import com.application.blog.velvetvoices.exceptions.ResourceNotFoundException;
import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.User.UserResponseDto;
import com.application.blog.velvetvoices.model.role.Role;
import com.application.blog.velvetvoices.model.role.RoleRequestDto;
import com.application.blog.velvetvoices.model.role.RoleResponseDto;
import com.application.blog.velvetvoices.repository.RoleRepository;
import com.application.blog.velvetvoices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RoleServiceImpl implements RoleService{

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    private Logger logger = LoggerFactory.getLogger(RoleServiceImpl.class);

    @Override
    public RoleResponseDto getRole(Long roleId) {
        logger.info("Inside getRole method of RoleServiceImpl");
        logger.info("Getting role with id: {}", roleId);
        Role role = this.roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "id", roleId));
        logger.info("Role found: {}", role);
        return RoleResponseDto.builder()
                .roleName(role.getRoleName())
                .roleDescription(role.getRoleDescription())
                .roleId(role.getRoleId())
                .users(role.getUsers())
                .build();

    }

    @Override
    public RoleResponseDto getRoleByName(String roleName) {
        logger.info("Inside getRoleByName method of RoleServiceImpl");
        logger.info("Getting role with name: {}", roleName);
        Optional<Role> role = Optional.ofNullable(this.roleRepository.findRoleByRoleName(roleName));
        if(role.isEmpty()){
            logger.error("Role with name {} not found", roleName);
            throw new ResourceNotFoundException("Role does not exist with Role name :" + roleName );
        }
        logger.info("Role found: {}", role);
        return RoleResponseDto.builder()
                .roleName(role.get().getRoleName())
                .roleDescription(role.get().getRoleDescription())
                .roleId(role.get().getRoleId())
                .users(role.get().getUsers())
                .build();
    }

    @Override
    public RoleResponseDto createRole(RoleRequestDto roleRequestDto) {
        logger.info("Inside createRole method of RoleServiceImpl");
        logger.info("Creating role with name: {}", roleRequestDto.getRoleName());
        Role role = Role.builder().roleName(roleRequestDto.getRoleName()).roleDescription(roleRequestDto.getRoleDescription()).build();
        this.roleRepository.save(role);
        logger.info("Role created: {}", role);
        return  RoleResponseDto.builder()
                .roleName(role.getRoleName())
                .roleDescription(role.getRoleDescription())
                .roleId(role.getRoleId())
                .users(role.getUsers())
                .build();
    }

    @Override
    public RoleResponseDto updateRole(RoleRequestDto roleRequestDto, Long roleId) {
        logger.info("Inside updateRole method of RoleServiceImpl");
        logger.info("Updating role with id: {}", roleId);
        Role role = this.roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "id", roleId));
        role.setRoleName(roleRequestDto.getRoleName());
        role.setRoleDescription(roleRequestDto.getRoleDescription());
        role.setRoleId(roleId);
        role.setUsers(roleRequestDto.getUsers());
        Role savedRole = this.roleRepository.save(role);
        logger.info("Role updated: {}", savedRole);
        return RoleResponseDto.builder()
                .roleName(savedRole.getRoleName())
                .roleDescription(savedRole.getRoleDescription())
                .roleId(savedRole.getRoleId())
                .users(savedRole.getUsers())
                .build();
    }

    @Override
    public String deleteRole(Long roleId) {
        logger.info("Inside deleteRole method of RoleServiceImpl");
        logger.info("Deleting role with id: {}", roleId);
        Role role = this.roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "id", roleId));
        logger.info("Role found: {}", role);
        this.roleRepository.deleteById(roleId);
        logger.info("Role deleted successfully");
        return "Role has been successfully deleted.";
    }

    @Override
    public String deleteRoleByName(String roleName) {
        logger.info("Inside deleteRoleByName method of RoleServiceImpl");
        logger.info("Deleting role with name: {}", roleName);
        Optional<Role> role = Optional.ofNullable(this.roleRepository.findRoleByRoleName(roleName));
        if(role.isEmpty()){
            logger.error("Role with name {} not found", roleName);
            throw new ResourceNotFoundException("Role does not exist with Role name :" + roleName );
        }
        logger.info("Role found: {}", role);
        this.roleRepository.delete(role.get());
        logger.info("Role deleted successfully");
        return "Role has been deleted successfully";
    }

    @Override
    public List<RoleResponseDto> getAllRoles() {
        logger.info("Inside getAllRoles method of RoleServiceImpl");
        List<Role> roles = this.roleRepository.findAll();
        List<RoleResponseDto> roleResponseDtos  = new ArrayList<>();
        for(Role role : roles){
            roleResponseDtos.add(RoleResponseDto.builder()
                    .roleName(role.getRoleName())
                    .roleDescription(role.getRoleDescription())
                    .roleId(role.getRoleId())
                    .users(role.getUsers())
                    .build());
        }

        logger.info("Roles fetched successfully");
        return roleResponseDtos;
    }

    @Override
    public List<RoleResponseDto> getRolesByUser(Long userId) {
        logger.info("Inside getRolesByUser method of RoleServiceImpl");
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
        logger.info("User found: {}", user);
        List<Role> roles = this.roleRepository.findRolesByUsers(user);
        logger.info("Roles found: {}", roles);
        List<RoleResponseDto> roleResponseDtos = new ArrayList<>();
        for(Role role : roles) {
            roleResponseDtos.add(RoleResponseDto.builder()
                    .roleName(role.getRoleName())
                    .roleDescription(role.getRoleDescription())
                    .roleId(role.getRoleId())
                    .users(role.getUsers())
                    .build());
        }
        logger.info("Roles fetched successfully");
        return roleResponseDtos;
    }

    @Override
    public List<UserResponseDto> getUsersByRoleId(Long roleId) {
        logger.info("Inside getUsersByRoleId method of RoleServiceImpl");
        Role role = this.roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "id", roleId));
        logger.info("Role found: {}", role);
        List<User> usersByRole = this.userRepository.findUsersByRoles(role);
        logger.info("Users found: {}", usersByRole);
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for(User user : usersByRole){
            userResponseDtos.add(UserResponseDto.builder()
                    .userId(user.getUserId())
                    .firstName(user.getFirstName())
                    .username(user.getUsername())
                    .userEmail(user.getUserEmail())
                    .lastName(user.getLastName())
                    .password(user.getPassword())
                    .about(user.getAbout())
                    .posts(user.getPosts())
                    .build());
        }
        logger.info("Users fetched successfully");
        return userResponseDtos;
    }

    @Override
    public UserResponseDto addRoleToUser(Long userId, Long roleId) {
        logger.info("Inside addRoleToUser method of RoleServiceImpl");
        User user = this.userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));
        logger.info("User found: {}", user);
        Role role = this.roleRepository.findById(roleId).orElseThrow(
                () -> new ResourceNotFoundException("Role", "id", roleId));
        logger.info("Role found: {}", role);
        if(user.getRoles().contains(role)){
            logger.error("User already has role with id: {}", roleId);
            throw new ResourceConflictException("User already has role with id: " + roleId);
        }

        user.getRoles().add(role);
        User save = this.userRepository.save(user);
        logger.info("Role added to user successfully");
        return UserResponseDto.builder()
                .userId(save.getUserId())
                .userEmail(save.getUserEmail())
                .username(save.getUsername())
                .firstName(save.getFirstName())
                .lastName(save.getLastName())
                .password(save.getPassword())
                .about(save.getAbout())
                .posts(save.getPosts())
                .build();
    }

    @Override
    public List<RoleResponseDto> saveAllRoles(List<RoleRequestDto> roleRequestDtos) {
        logger.info("Inside saveAllRoles method of RoleServiceImpl");
        logger.info("Saving roles");
        logger.info("Roles to be saved: {}", roleRequestDtos);
        List<Role> roles = new ArrayList<>();
        for(RoleRequestDto roleRequestDto : roleRequestDtos){
            roles.add(Role.builder()
                    .roleName(roleRequestDto.getRoleName())
                    .roleDescription(roleRequestDto.getRoleDescription())
                    .build());
        }

        List<Role> saveAll = this.roleRepository.saveAll(roles);
        List<RoleResponseDto> roleResponseDtos = new ArrayList<>();

        for(Role role : saveAll){
            roleResponseDtos.add(RoleResponseDto.builder()
                    .roleName(role.getRoleName())
                    .roleDescription(role.getRoleDescription())
                    .roleId(role.getRoleId())
                    .build());
        }
        logger.info("Roles saved successfully");
        return roleResponseDtos;
    }
}
