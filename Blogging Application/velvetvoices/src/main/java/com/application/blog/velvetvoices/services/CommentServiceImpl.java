package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.exceptions.ResourceNotFoundException;
import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.comment.Comment;
import com.application.blog.velvetvoices.model.comment.CommentRequestDto;
import com.application.blog.velvetvoices.model.comment.CommentResponseDto;
import com.application.blog.velvetvoices.model.post.Post;
import com.application.blog.velvetvoices.repository.CommentRepository;
import com.application.blog.velvetvoices.repository.PostRepository;
import com.application.blog.velvetvoices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    private Logger  logger =  LoggerFactory.getLogger(CommentServiceImpl.class);
    @Override
    public CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long postId, Long userId) {
        logger.info("Inside addComment method of CommentServiceImpl");
        User user =userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        Comment comment = new Comment();
        comment.setContent(commentRequestDto.getContent());
        comment.setPost(post);
        comment.setUser(user);
        comment.setAddedDate(LocalDate.now());

        Comment savedComment = this.commentRepository.save(comment);

        logger.info("Comment saved successfully");
        return CommentResponseDto.builder()
                .commentId(savedComment.getCommentId())
                .content(savedComment.getContent())
                .post(savedComment.getPost())
                .user(savedComment.getUser())
                .build();
    }

    @Override
    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, Long commentId) {
        logger.info("Inside updateComment method of CommentServiceImpl");
        logger.info("Comment id: {}", commentId);
        logger.info("Comment content: {}", commentRequestDto.getContent());
        logger.info("Comment post: {}", commentRequestDto.getPost());
        logger.info("Comment user: {}", commentRequestDto.getUser());
        logger.info("Comment added date: {}", commentRequestDto.getAddedDate());
        Comment commentFound = this.commentRepository.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
        commentFound.setContent(commentRequestDto.getContent());
        commentFound.setPost(commentRequestDto.getPost());
        commentFound.setUser(commentRequestDto.getUser());
        commentFound.setCommentId(commentId);

        Comment updatedComment = this.commentRepository.save(commentFound);
        logger.info("Comment updated successfully");
        return CommentResponseDto.builder()
                .commentId(updatedComment.getCommentId())
                .content(updatedComment.getContent())
                .post(updatedComment.getPost())
                .user(updatedComment.getUser())
                .build();
    }

    @Override
    public String deleteComment(Long commentId) {
        logger.info("Inside deleteComment method of CommentServiceImpl");
        Optional<Comment> commentFound = this.commentRepository.findById(commentId);
        if(commentFound.isEmpty()) {
            logger.error("Comment with id {} not found", commentId);
            throw new ResourceNotFoundException("Comment", "id", commentId);
        }
        this.commentRepository.delete(commentFound.get());
        logger.info("Comment deleted successfully");
        return "Comment deleted successfully";
    }

    @Override
    public CommentResponseDto getCommentById(Long commentId) {
        logger.info("Inside getCommentById method of CommentServiceImpl");
        Optional<Comment> commentFound = this.commentRepository.findById(commentId);
        if(commentFound.isEmpty()) {
            logger.error("Comment with id {} not found", commentId);
            throw new ResourceNotFoundException("Comment", "id", commentId);
        }
        return CommentResponseDto.builder()
                .commentId(commentFound.get().getCommentId())
                .content(commentFound.get().getContent())
                .post(commentFound.get().getPost())
                .user(commentFound.get().getUser())
                .build();

    }


    @Override
    public List<CommentResponseDto> getAllComments() {
        logger.info("Inside getAllComments method of CommentServiceImpl");
        List<Comment> comments = this.commentRepository.findAll();
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : comments) {
            commentResponseDtos.add(CommentResponseDto.builder()
                    .commentId(comment.getCommentId())
                    .content(comment.getContent())
                    .post(comment.getPost())
                    .user(comment.getUser())
                    .build());
        }
        logger.info("Comments fetched successfully");
        return commentResponseDtos;
    }

    @Override
    public List<CommentResponseDto> getCommentsByPostId(Long postId) {
        logger.info("Inside getCommentsByPostId method of CommentServiceImpl");
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        List<Comment> byPost = commentRepository.findByPost(post);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : byPost) {
            commentResponseDtos.add(CommentResponseDto.builder()
                    .commentId(comment.getCommentId())
                    .content(comment.getContent())
                    .post(comment.getPost())
                    .user(comment.getUser())
                    .build());
        }

        logger.info("Comments fetched successfully");
        return commentResponseDtos;
    }

    @Override
    public List<CommentResponseDto> getCommentsByUserId(Long userId) {
        logger.info("Inside getCommentsByUserId method of CommentServiceImpl");
        logger.info("User id: {}", userId);
        User user = this.userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));

        logger.info("Comments fetched successfully");

        List<Comment> byUser = this.commentRepository.findByUser(user);
        List<CommentResponseDto> commentResponseDtos = new ArrayList<>();
        for (Comment comment : byUser) {
            commentResponseDtos.add(CommentResponseDto.builder()
                    .commentId(comment.getCommentId())
                    .content(comment.getContent())
                    .post(comment.getPost())
                    .user(comment.getUser())
                    .build());
        }

        return commentResponseDtos;
    }

}
