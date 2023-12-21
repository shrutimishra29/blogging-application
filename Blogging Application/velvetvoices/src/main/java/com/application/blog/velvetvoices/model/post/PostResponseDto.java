package com.application.blog.velvetvoices.model.post;

import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.category.Category;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;
import java.time.LocalDate;

@Data
@Builder
public class PostResponseDto {

    private Long postId;

    private String title;

    @JsonIgnore
    private String content;

    private String imageUrl;


    private LocalDate addedDate;

    private User user;

    private Category category;
}
