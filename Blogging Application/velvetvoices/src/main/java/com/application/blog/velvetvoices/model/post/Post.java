package com.application.blog.velvetvoices.model.post;

import com.application.blog.velvetvoices.model.comment.Comment;
import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.category.Category;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
@Entity
@Table(name = "post")
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "post_id")
    private Long postId;

    @Column(name = "title")
    private String title;

    @Column(name = "content", length = 50000, columnDefinition = "TEXT" , nullable = false, unique = false)
    private String content;

    @Column(name = "added_date")
    private LocalDate addedDate;

    private String imageUrl;

    @ManyToOne
    @JsonBackReference(value = "user_post")
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "category_id")
    @JsonBackReference(value = "category_post")
    private Category category;

    @OneToMany(mappedBy = "post")
    @JsonManagedReference(value = "post_comment")
    @OrderBy("commentId DESC")
    @Column(name = "comments")
    @Transient
    private List<Comment> comments;

}
