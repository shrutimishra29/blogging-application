package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.model.comment.CommentRequestDto;
import com.application.blog.velvetvoices.model.comment.CommentResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CommentService {

    public CommentResponseDto addComment(CommentRequestDto commentRequestDto, Long postId, Long userId);

    public CommentResponseDto updateComment(CommentRequestDto commentRequestDto, Long commentId);


    public String deleteComment(Long commentId);


    public CommentResponseDto getCommentById(Long commentId);


    public List<CommentResponseDto> getAllComments();

    public List<CommentResponseDto> getCommentsByPostId(Long postId);


    public List<CommentResponseDto> getCommentsByUserId(Long userId);
}
