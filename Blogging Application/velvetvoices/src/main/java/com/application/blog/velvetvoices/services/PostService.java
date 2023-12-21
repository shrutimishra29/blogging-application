package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.model.post.PostRequestDto;
import com.application.blog.velvetvoices.model.post.PostResponseDto;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.InputStream;

import java.util.List;

@Service
public interface PostService {

    public PostResponseDto createPost(PostRequestDto postRequestDto, Long categoryId, Long userId);

    public PostResponseDto updatePost(PostRequestDto postRequestDto, Long postId);

    public String deletePost(Long postId);

    public PostResponseDto getPost(Long postId);

    public PostResponseDto getPostByTitle(String title);

    public List<PostResponseDto> getPostByCategoryId(Long categoryId);

    public List<PostResponseDto> getPostByUserId(Long user);


    public List<PostResponseDto> getAllPost();

    public List<PostResponseDto> searchPost(String keyword);

    public InputStream downloadImageFromPost(Long postId) throws IOException;

}
