package com.application.blog.velvetvoices.repository;

import com.application.blog.velvetvoices.model.category.Category;
import com.application.blog.velvetvoices.model.post.Post;
import com.application.blog.velvetvoices.model.User.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepository extends JpaRepository<Post, Long> {
    Post findByTitle(String title);

    List<Post> findByCategory(Category category);

    List<Post> findByUser(User user);

    List<Post> findByTitleContaining(String keyword);
}
