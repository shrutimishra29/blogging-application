package com.application.blog.velvetvoices.controller;

import com.application.blog.velvetvoices.model.User.UserResponseDto;
import com.application.blog.velvetvoices.model.role.RoleRequestDto;
import com.application.blog.velvetvoices.model.role.RoleResponseDto;
import com.application.blog.velvetvoices.services.RoleService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/roles")
@SecurityRequirement(name = "velvetvoiceapi")
@CrossOrigin(origins = "*")
public class RoleController {

    @Autowired
    private RoleService roleService;

    private Logger logger = LoggerFactory.getLogger(RoleController.class);

    @PostMapping("/create")
    public RoleResponseDto addNewRole(@RequestBody RoleRequestDto roleRequestDto) {
        logger.info("Inside addNewRole method of RoleController");
        logger.info("Role Request Dto : {}",roleRequestDto);
        return roleService.createRole(roleRequestDto);
    }

    @PostMapping("/delete/{roleId}")
    public String deleteRole(@PathVariable("roleId") Long roleId) {
        logger.info("Inside deleteRole method of RoleController");
        logger.info("Role Id : {}",roleId);
        return roleService.deleteRole(roleId);
    }


    @PostMapping("/deleteByName")
    public String deleteRoleByName(@RequestBody String roleName) {
        logger.info("Inside deleteRoleByName method of RoleController");
        logger.info("Role Name : {}",roleName);
        return roleService.deleteRoleByName(roleName);
    }

    @GetMapping("/getAll/roles")
    public List<RoleResponseDto> getAllRoles() {
        logger.info("Inside getAllRoles method of RoleController");
        return roleService.getAllRoles();
    }

    @PutMapping("/user/{userId}/role/{roleId}")
    public UserResponseDto addRoleToUser(@PathVariable("userId") Long userId, @PathVariable("roleId") Long roleId) {
        logger.info("Inside addRoleToUser method of RoleController");
        logger.info("User Id : {}",userId);
        logger.info("Role Id : {}",roleId);
        return roleService.addRoleToUser(userId, roleId);
    }

    @PostMapping("/saveAll")
    public List<RoleResponseDto> saveAllRoles(@RequestBody List<RoleRequestDto> roleRequestDtos) {
        logger.info("Inside saveAllRoles method of RoleController");
        logger.info("Role Request Dtos : {}",roleRequestDtos);
        return roleService.saveAllRoles(roleRequestDtos);
    }

    @GetMapping("/getUsersByRoleId/{roleId}")
    public List<UserResponseDto> getUsersByRoleId(@PathVariable("roleId") Long roleId) {
        logger.info("Inside getUsersByRoleId method of RoleController");
        logger.info("Role Id : {}",roleId);
        return roleService.getUsersByRoleId(roleId);
    }


    @GetMapping("/getRolesByUser/{userId}")
    public List<RoleResponseDto> getRolesByUser(@PathVariable("userId") Long userId) {
        logger.info("Inside getRolesByUser method of RoleController");
        logger.info("User Id : {}",userId);
        return roleService.getRolesByUser(userId);
    }


}
