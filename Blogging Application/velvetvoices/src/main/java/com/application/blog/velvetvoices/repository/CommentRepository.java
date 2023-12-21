package com.application.blog.velvetvoices.repository;

import com.application.blog.velvetvoices.model.comment.Comment;
import com.application.blog.velvetvoices.model.post.Post;
import com.application.blog.velvetvoices.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    List<Comment> findByUser(User user);

    List<Comment> findByPost(Post post);
}
