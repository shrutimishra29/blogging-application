package com.application.blog.velvetvoices.model.comment;

import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.post.Post;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;


@Table
@Entity(name = "comments")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@Builder
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(name = "content", length = 50000, columnDefinition = "TEXT" , nullable = false, unique = false)
    private String content;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id", referencedColumnName = "userId")
    @MapsId
    private User user;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "post_id")
    @JsonBackReference(value = "post_comment")
    private Post post;

    private LocalDate addedDate;
}
