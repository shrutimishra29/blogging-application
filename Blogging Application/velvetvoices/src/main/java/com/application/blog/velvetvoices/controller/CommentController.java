package com.application.blog.velvetvoices.controller;

import com.application.blog.velvetvoices.model.comment.CommentRequestDto;
import com.application.blog.velvetvoices.model.comment.CommentResponseDto;
import com.application.blog.velvetvoices.services.CommentService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/comments")
@SecurityRequirement(name = "velvetvoiceapi")
@CrossOrigin(origins = "*")
public class CommentController {

    @Autowired
    private CommentService commentService;

    private Logger logger = LoggerFactory.getLogger(CommentController.class);

    @PostMapping("/addComment/{postId}/user/{userId}")
    public CommentResponseDto addComment(@RequestBody CommentRequestDto commentRequestDto, @PathVariable("postId") Long postId, @PathVariable("userId") Long userId){
        logger.info("Inside addComment method");
        logger.info("Comment Request Dto : {}",commentRequestDto);
        logger.info("Post Id : {}",postId);
        logger.info("User Id : {}",userId);
        return commentService.addComment(commentRequestDto, postId, userId);
    }

    @GetMapping("/getCommentsByPostId/{postId}")
    public List<CommentResponseDto> getCommentsByPostId(@PathVariable("postId") Long postId){
        logger.info("Inside getCommentsByPostId method");
        logger.info("Post Id : {}",postId);
        return commentService.getCommentsByPostId(postId);
    }

    @GetMapping("/getCommentsByUserId/{userId}")
    public List<CommentResponseDto> getCommentsByUserId(@PathVariable("userId") Long userId){
        logger.info("Inside getCommentsByUserId method");
        logger.info("User Id : {}",userId);
        return commentService.getCommentsByUserId(userId);
    }


    @DeleteMapping("/deleteComment/{commentId}")
    public String deleteComment(@PathVariable("commentId") Long commentId){
        logger.info("Inside deleteComment method");
        logger.info("Comment Id : {}",commentId);
        return commentService.deleteComment(commentId);
    }

    @GetMapping("/getCommentById/{commentId}")
    public CommentResponseDto getCommentById(@PathVariable("commentId") Long commentId){
        logger.info("Inside getCommentById method");
        logger.info("Comment Id : {}",commentId);
        return commentService.getCommentById(commentId);
    }
}
