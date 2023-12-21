package com.application.blog.velvetvoices;

import com.application.blog.velvetvoices.controller.RoleController;
import com.application.blog.velvetvoices.model.role.RoleResponseDto;
import com.application.blog.velvetvoices.services.RoleService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class VelvetvoicesApplicationTests {


	@Autowired
	private RoleService roleService;

	@Autowired
	private RoleController roleController;

	@Test
	void contextLoads() {
	}

	@Test
	void testGetAllRoles() {
		List<RoleResponseDto> allRoles = roleService.getAllRoles();
		for (RoleResponseDto role : allRoles) {
			System.out.println(role.getRoleName());
		}

	}

	@Test
	void testGetAllRolesController() {
		List<RoleResponseDto> allRoles = roleController.getAllRoles();
		for (RoleResponseDto role : allRoles) {
			System.out.println(role.getRoleName());
		}

	}


}
