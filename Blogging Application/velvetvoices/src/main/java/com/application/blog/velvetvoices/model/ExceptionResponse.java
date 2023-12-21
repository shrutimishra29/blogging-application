package com.application.blog.velvetvoices.model;


import lombok.*;
import org.springframework.http.HttpStatus;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ExceptionResponse {

    private String message;

    private HttpStatus httpStatus;

    private Boolean success;

}
