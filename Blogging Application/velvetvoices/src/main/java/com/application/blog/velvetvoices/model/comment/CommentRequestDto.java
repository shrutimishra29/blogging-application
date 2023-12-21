package com.application.blog.velvetvoices.model.comment;

import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.post.Post;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.Date;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentRequestDto {

    private Long commentId;

    @NotBlank
    @NotBlank
    @Size(max = 1000, min = 1, message = "Comment must be between 1 and 1000 characters")
    private String content;

    private User user;

    private Post post;

    @PastOrPresent(message = "Added date cannot be in the future")
    private Date addedDate;
}
