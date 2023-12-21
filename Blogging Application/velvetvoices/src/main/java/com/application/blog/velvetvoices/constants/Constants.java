package com.application.blog.velvetvoices.constants;

import java.util.List;

public class Constants {

    public static final String imagePath = "C:\\Users\\muska\\OneDrive\\Desktop\\Files\\Blogging Application\\Blogging Application\\velvetvoices\\src\\main\\resources\\images";

    public static final List<String> PUBLIC_URLS = List.of(
            "/api/v1/auth/**",
            "/v3/api-docs/**",
            "/swagger-ui/**",
            "/api/v1/user/create-user",
            "/webjars/**",
            "/swagger-resources/**"
    );
}
