package com.application.blog.velvetvoices.model.post;

import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Data;

import java.util.Date;

@Data
@Builder
public class PostRequestDto {

    private Long postId;

    @Size(min = 3, max = 50, message = "Title must be between 3 and 50 characters.")
    private String title;

    @Size(min = 10, message = "Content must be at least 10 characters.")
    @Size(max = 10000, message = "Content must be less than 1000 characters.")  // 1000 is the maximum length of a post content.
    private String content;

    private Date addedDate;

    @JsonBackReference(value = "category-post")
    private Category category;

    @JsonBackReference(value = "user-post")
    private User user;

    private String imageUrl;
}
