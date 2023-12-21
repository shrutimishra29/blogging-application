package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.exceptions.ResourceNotFoundException;
import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.User.UserRequestDto;
import com.application.blog.velvetvoices.model.User.UserResponseDto;
import com.application.blog.velvetvoices.model.role.Role;
import com.application.blog.velvetvoices.repository.RoleRepository;
import com.application.blog.velvetvoices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Autowired
    private RoleRepository roleRepository;

    private Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

    @Override
    public UserResponseDto saveUser(UserRequestDto userRequestDto) {
        logger.info("Inside saveUser method of UserServiceImpl");
        ArrayList<Role> roles =  new ArrayList<>();
        roles.add(roleRepository.findRoleByRoleName("ROLE_USER"));
        logger.info("roles: {}", roles);
        User user1 = User.builder()
                .userEmail(userRequestDto.getUserEmail())
                .firstName(userRequestDto.getFirstName())
                .lastName(userRequestDto.getLastName())
                .username(userRequestDto.getUsername())
                .password(passwordEncoder.encode(userRequestDto.getPassword()))
                .about(userRequestDto.getAbout())
                .roles(roles)
                .posts(userRequestDto.getPosts())
                .build();

        logger.info("User saved : {}" , user1);
        userRepository.save(user1);
        logger.info("User saved successfully");
        return UserResponseDto.builder()
                .userId(user1.getUserId())
                .userEmail(user1.getUserEmail())
                .firstName(user1.getFirstName())
                .username(user1.getUsername())
                .lastName(user1.getLastName())
                .password(user1.getPassword())
                .about(user1.getAbout())
                .posts(user1.getPosts())
                .build();
    }

    @Override
    public UserResponseDto findByEmail(String email) {
        logger.info("Inside findByEmail method of UserServiceImpl");
        logger.info("Email : {}", email);
        User userByEmail = userRepository.findUserByUserEmail(email);
        if(userByEmail == null){
            logger.info("User with email : {} doesn't exit", email);
            throw new ResourceNotFoundException("User with email : " + email + " doesn't exit");
        }
        logger.info("User found : {}", userByEmail);
        return UserResponseDto.builder()
                .userId(userByEmail.getUserId())
                .userEmail(userByEmail.getUserEmail())
                .username(userByEmail.getUsername())
                .firstName(userByEmail.getFirstName())
                .lastName(userByEmail.getLastName())
                .password(userByEmail.getPassword())
                .about(userByEmail.getAbout())
                .build();
    }


    @Override
    public UserResponseDto findById(Long id) {
        logger.info("Inside findById method of UserServiceImpl");
        logger.info("Id : {}", id);
        User user = userRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("User", "id", id));
        logger.info("User found : {}", user);
        return UserResponseDto.builder()
                .userId(user.getUserId())
                .userEmail(user.getUserEmail())
                .username(user.getUsername())
                .firstName(user.getFirstName())
                .lastName(user.getLastName())
                .password(user.getPassword())
                .about(user.getAbout())
                .posts(user.getPosts())
                .build();
    }

    @Override
    public UserResponseDto updateUser(UserRequestDto userRequestDto, long userId) {
        logger.info("Inside updateUser method of UserServiceImpl");
        logger.info("User id : {}", userId);
        Optional<User> user = userRepository.findById(userId);
        if(user.isEmpty()){
            logger.info("User with id : {} doesn't exit", userId);
            throw new ResourceNotFoundException("User", "id", userId);
        }
        User user1 = user.get();
        user1.setFirstName(userRequestDto.getFirstName());
        user1.setLastName(userRequestDto.getLastName());
        user1.setUsername(userRequestDto.getUsername());
        user1.setPassword(passwordEncoder.encode(userRequestDto.getPassword()));
        user1.setAbout(userRequestDto.getAbout());
        user1.setPosts(userRequestDto.getPosts());
        logger.info("User updated : {}", user1);
        userRepository.save(user1);

        return UserResponseDto.builder()
                .userId(user1.getUserId())
                .userEmail(user1.getUserEmail())
                .username(user1.getUsername())
                .firstName(user1.getFirstName())
                .lastName(user1.getLastName())
                .password(user1.getPassword())
                .about(user1.getAbout())
                .posts(user1.getPosts())
                .build();
    }



    @Override
    public String deleteUser(Long userId) {
        logger.info("Inside deleteUser method of UserServiceImpl");
        Optional<User> user = userRepository.findById(userId);
        logger.info("User id : {}", userId);
        if(user.isEmpty()){
            logger.info("User with id : {} doesn't exit", userId);
            throw new ResourceNotFoundException("User", "id", userId);
        }
       String email = user.get().getUserEmail();
        userRepository.delete(user.get());

        return "User with email" + email + "has been deleted.";

    }

    @Override
    public boolean isUsernameAvailable(String username) {
        logger.info("Inside isUsernameAvailable method of UserServiceImpl");
        logger.info("Username : {}", username);
        User userByUsername = userRepository.findUserByUsername(username);
        if(userByUsername == null){
            logger.info("Username : {} is available", username);
            return true;
        }
        logger.info("Username : {} is not available", username);
        return false;
    }

    @Override
    public boolean isEmailAvailable(String email) {
        logger.info("Inside isEmailAvailable method of UserServiceImpl");
        logger.info("Email : {}", email);
        User userByEmail = userRepository.findUserByUserEmail(email);
        if(userByEmail == null){
            logger.info("Email : {} is available", email);
            return true;
        }
        logger.info("Email : {} is not available", email);
        return false;
    }

    @Override
    public UserResponseDto findByUsername(String username) {
        logger.info("Inside findByUsername method of UserServiceImpl");
        logger.info("Username : {}", username);
        User userByUsername = this.userRepository.findUserByUserEmail(username);
        if(userByUsername == null){
            logger.info("User with username : {} doesn't exit", username);
            throw new ResourceNotFoundException("User with username : " + username + " doesn't exit");
        }

        logger.info("User found : {}", userByUsername);
        return UserResponseDto.builder()
                .userId(userByUsername.getUserId())
                .userEmail(userByUsername.getUserEmail())
                .username(userByUsername.getUsername())
                .firstName(userByUsername.getFirstName())
                .lastName(userByUsername.getLastName())
                .password(userByUsername.getPassword())
                .about(userByUsername.getAbout())
                .posts(userByUsername.getPosts())
                .build();

    }

    @Override
    public List<UserResponseDto> getAllUsers() {
        logger.info("Inside getAllUsers method of UserServiceImpl");
        List<User> users = userRepository.findAll();
        List<UserResponseDto> userResponseDtos = new ArrayList<>();

        for(User user : users){
            userResponseDtos.add(UserResponseDto.builder()
                    .userId(user.getUserId())
                    .userEmail(user.getUserEmail())
                    .username(user.getUsername())
                    .firstName(user.getFirstName())
                    .lastName(user.getLastName())
                    .password(user.getPassword())
                    .about(user.getAbout())
                    .posts(user.getPosts())
                    .build());
        }
        logger.info("Users found : {}", userResponseDtos);
        return userResponseDtos;
    }

    @Override
    public List<UserResponseDto> saveAllUsers(List<UserRequestDto> userRequestDtos) {
        logger.info("Inside saveAllUsers method of UserServiceImpl");
        List<UserResponseDto> userResponseDtos = new ArrayList<>();
        for (UserRequestDto userRequestDto : userRequestDtos) {
            UserResponseDto userResponseDto = saveUser(userRequestDto);
            userResponseDtos.add(userResponseDto);
        }
        logger.info("Users saved : {}", userResponseDtos);
        return userResponseDtos;
    }
}
