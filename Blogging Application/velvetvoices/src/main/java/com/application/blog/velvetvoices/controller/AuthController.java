package com.application.blog.velvetvoices.controller;

import com.application.blog.velvetvoices.model.JwtRequest;
import com.application.blog.velvetvoices.model.JwtResponse;
import com.application.blog.velvetvoices.security.JwtHelper;
import com.application.blog.velvetvoices.services.MyUserDetailsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.stream.Stream;

@RestController
@RequestMapping("/api/v1/auth")
@CrossOrigin(origins = "*")
public class AuthController {

    @Autowired
    private MyUserDetailsService userDetailsService;

    @Autowired
    private AuthenticationManager authenticationManager;

    private Logger logger =  LoggerFactory.getLogger(AuthController.class);
    @Autowired
    private JwtHelper helper;


    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest request) {

        logger.info("Inside login method of AuthController");
        logger.info("Email : ", request.getEmail());
        this.doAuthenticate(request.getEmail(), request.getPassword());
        UserDetails userDetails = userDetailsService.loadUserByUsername(request.getEmail());
        String token = this.helper.generateToken(userDetails);
        final String[] roles = {""};
        Stream<String> stringStream = userDetails.getAuthorities().stream().map(GrantedAuthority::getAuthority);
        stringStream.forEach(s -> roles[0] += s + " ");
        JwtResponse response = JwtResponse.builder()
                .jwtToken(token)
                .username(userDetails.getUsername())
                .role(Arrays.toString(roles))
                .build();
        return new ResponseEntity<>(response, HttpStatus.OK);
    }


    private void doAuthenticate(String email, String password) {

       logger.info("Inside doAuthenticate method of AuthController");
        logger.info("Email : {}",email);
        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(email, password);
        try {
            authenticationManager.authenticate(authentication);


        } catch (BadCredentialsException | IllegalArgumentException e) {
            logger.info("Invalid Credentials !!");
            throw new BadCredentialsException(" Invalid Username or Password  !!");
        }

    }


}
