package com.application.blog.velvetvoices.exceptions;


import lombok.*;

@Getter
@Setter
public class UserWithEmailExistsException extends RuntimeException {


    private String email;

    private String message;

    public UserWithEmailExistsException(String email) {
        this.email = email;
        this.message = "User with email : " + email + " already exists. ";
    }
}
