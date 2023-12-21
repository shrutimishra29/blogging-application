package com.application.blog.velvetvoices.controller;
import com.application.blog.velvetvoices.model.User.UserRequestDto;
import com.application.blog.velvetvoices.model.User.UserResponseDto;
import com.application.blog.velvetvoices.services.UserServiceImpl;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/user")
//@SecurityRequirement(name = "velvetvoiceapi")
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {

    @Autowired
    private UserServiceImpl userService;

    @GetMapping("/test")
    public String testApi(){
        return "API is working";
    }

    private Logger logger = LoggerFactory.getLogger(UserController.class);

    @PostMapping("/create-user")
    public UserResponseDto createUser(@Valid @RequestBody UserRequestDto userRequestDto){
        logger.info("Inside createUser method of UserController");
        logger.info("User Request Dto : {}",userRequestDto);
        return userService.saveUser(userRequestDto);
    }

    @GetMapping("/get-user/{userId}")
    public UserResponseDto getUser(@PathVariable Long userId){
        logger.info("Inside getUser method of UserController");
        logger.info("User Id : {}",userId);
        return userService.findById(userId);
    }


    @PutMapping("/update-user/{userId}")
    public UserResponseDto updateUser(@PathVariable long userId,@Valid @RequestBody UserRequestDto userRequestDto){
        logger.info("Inside updateUser method of UserController");
        logger.info("User Request Dto : {}",userRequestDto);
        return userService.updateUser(userRequestDto, userId);
    }


    @DeleteMapping("/delete-user/{userId}")
    public String deleteUser(@PathVariable long userId){
        logger.info("Inside deleteUser method of UserController");
        logger.info("User Id : {}",userId);
        return userService.deleteUser(userId);  //returning string because we are returning the message to the user.
    }

    @GetMapping("/get-all-users")
    public List<UserResponseDto> getAllUsers(){
        logger.info("Inside getAllUsers method of UserController");
        return userService.getAllUsers();  //returning string because we are returning the message to the user.
    }

    @PostMapping("/save-all-users")
    public List<UserResponseDto> saveAllUsers(@RequestBody List<UserRequestDto> userRequestDtos){
            logger.info("Inside saveAllUsers method of UserController");
            return userService.saveAllUsers(userRequestDtos);  //returning string because we are returning the message to the user
    }

}
