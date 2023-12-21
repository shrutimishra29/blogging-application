package com.application.blog.velvetvoices.model.comment;

import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.post.Post;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CommentResponseDto {

    private Long commentId;

    private User user;

    @JsonIgnore
    private Post post;

    private String content;

    private LocalDateTime addedDate;
}
