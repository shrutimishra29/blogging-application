package com.application.blog.velvetvoices.services;
import com.application.blog.velvetvoices.model.User.UserRequestDto;
import com.application.blog.velvetvoices.model.User.UserResponseDto;

import java.util.List;

public interface UserService {

    public UserResponseDto saveUser(UserRequestDto user);
    public UserResponseDto findByEmail(String email);
    public UserResponseDto findById(Long id);
    public UserResponseDto updateUser(UserRequestDto userRequestDto, long userId);
    public String deleteUser(Long id);
    public boolean isUsernameAvailable(String username);
    public boolean isEmailAvailable(String email);

    public UserResponseDto findByUsername(String username);


    public List<UserResponseDto> getAllUsers();

    public List<UserResponseDto> saveAllUsers(List<UserRequestDto> userRequestDtos);


}
