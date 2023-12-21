package com.application.blog.velvetvoices.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JwtResponse {

    private String jwtToken;
    private String role;
    private String username;
}
