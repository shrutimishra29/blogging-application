package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.constants.Constants;
import com.application.blog.velvetvoices.exceptions.ResourceNotFoundException;
import com.application.blog.velvetvoices.model.User.User;
import com.application.blog.velvetvoices.model.category.Category;
import com.application.blog.velvetvoices.model.post.Post;
import com.application.blog.velvetvoices.model.post.PostRequestDto;
import com.application.blog.velvetvoices.model.post.PostResponseDto;
import com.application.blog.velvetvoices.repository.CategoryRepository;
import com.application.blog.velvetvoices.repository.PostRepository;
import com.application.blog.velvetvoices.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.io.InputStream;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Service
public class PostServiceImpl implements PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private FileService fileService;

    private Logger logger = LoggerFactory.getLogger(PostServiceImpl.class);

    @Override
    public PostResponseDto createPost(PostRequestDto postRequestDto, Long categoryId, Long userId) {
        logger.info("Inside createPost method of PostServiceImpl");
        logger.info("User id: " + userId);
        logger.info("Category id: " + categoryId);
        logger.info("Post title: " + postRequestDto.getTitle());
        logger.info("Post content: " + postRequestDto.getContent());
        logger.info("Post image url: " + postRequestDto.getImageUrl());
        User user = userRepository.findById(userId).orElseThrow(
                () -> new ResourceNotFoundException("User", "id", userId));

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));

        Post post = new Post();
        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setImageUrl(postRequestDto.getImageUrl());
        post.setUser(user);
        post.setAddedDate(LocalDate.now());
        post.setCategory(category);


        Post savedPost = postRepository.save(post);
        logger.info("Post saved successfully");
        return PostResponseDto.builder()
                .postId(savedPost.getPostId())
                .title(savedPost.getTitle())
                .content(savedPost.getContent())
                .addedDate(savedPost.getAddedDate())
                .imageUrl(savedPost.getImageUrl())
                .category(savedPost.getCategory())
                .build();
    }

    @Override
    public PostResponseDto updatePost(PostRequestDto postRequestDto, Long postId) {
        logger.info("Inside updatePost method of PostServiceImpl");
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));

        post.setTitle(postRequestDto.getTitle());
        post.setContent(postRequestDto.getContent());
        post.setImageUrl(postRequestDto.getImageUrl());
        post.setAddedDate(LocalDate.now());

        Post updatedPost = postRepository.save(post);
        logger.info("Post updated successfully");
        return PostResponseDto.builder()
                .postId(updatedPost.getPostId())
                .title(updatedPost.getTitle())
                .content(updatedPost.getContent())
                .addedDate(updatedPost.getAddedDate())
                .imageUrl(updatedPost.getImageUrl())
                .category(updatedPost.getCategory())
                .build();
    }

    @Override
    public String deletePost(Long postId) {
        logger.info("Inside deletePost method of PostServiceImpl");
        logger.info("Post id: " + postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        postRepository.delete(post);
        logger.info("Post deleted successfully");
        return "Post deleted successfully";
    }

    @Override
    public PostResponseDto getPost(Long postId) {
        logger.info("Inside getPost method of PostServiceImpl");
        logger.info("Post id: " + postId);
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
       logger.info("Post fetched successfully");
        return PostResponseDto.builder()
                .postId(post.getPostId())
                .user(post.getUser())
                .category(post.getCategory())
                .title(post.getTitle())
                .content(post.getContent())
                .addedDate(post.getAddedDate())
                .title(post.getTitle())
                .content(post.getContent())
                .addedDate(post.getAddedDate())
                .imageUrl(post.getImageUrl())
                .build();
    }

    @Override
    public PostResponseDto getPostByTitle(String title) {
        logger.info("Inside getPostByTitle method of PostServiceImpl");
        Post byTitle = postRepository.findByTitle(title);
        logger.info("Post fetched successfully");
        return PostResponseDto.builder()
                .postId(byTitle.getPostId())
                .title(byTitle.getTitle())
                .content(byTitle.getContent())
                .addedDate(byTitle.getAddedDate())
                .imageUrl(byTitle.getImageUrl())
                .category(byTitle.getCategory())
                .build();
    }

    @Override
    public List<PostResponseDto> getPostByCategoryId(Long categoryId) {
        logger.info("Inside getPostByCategoryId method of PostServiceImpl");
        Category category = categoryRepository.findById(categoryId).orElseThrow(() -> new ResourceNotFoundException("Category", "id", categoryId));
        List<Post> posts = postRepository.findByCategory(category);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        logger.info("Posts fetched successfully");
        for (Post post : posts) {
            postResponseDtos.add(PostResponseDto.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .addedDate(post.getAddedDate())
                    .category(post.getCategory())
                    .build());
        }

        return postResponseDtos;
    }

    @Override
    public List<PostResponseDto> getPostByUserId(Long userId) {
        logger.info("Inside getPostByUserId method of PostServiceImpl");
        logger.info("User id: " + userId);
        User user1 = userRepository.findById(userId).orElseThrow(() -> new ResourceNotFoundException("User", "id", userId));
        List<Post> byUser = postRepository.findByUser(user1);
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        logger.info("Posts fetched successfully");
        for (Post post : byUser) {
            postResponseDtos.add(PostResponseDto.builder()
                    .postId(post.getPostId())
                    .title(post.getTitle())
                    .content(post.getContent())
                    .addedDate(post.getAddedDate())
                    .category(post.getCategory())
                    .build());
        }

        return postResponseDtos;
    }

    @Override
    public List<PostResponseDto> getAllPost(){
logger.info("Inside getAllPost method of PostServiceImpl");
        List<Post> postList = postRepository.findAll();
        List<PostResponseDto> postResponseDtos = new ArrayList<>();
        postResponseDtos = postList.stream().map(post ->
                PostResponseDto.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .addedDate(post.getAddedDate())
                        .imageUrl(post.getImageUrl())
                        .category(post.getCategory())
                        .build()
        ).toList();
        logger.info("Posts fetched successfully");

        return postResponseDtos;
    }

    @Override
    public List<PostResponseDto> searchPost(String keyword) {
        logger.info("Inside searchPost method od PostServiceImpl");
        List<Post> byTitleContaining = postRepository.findByTitleContaining(keyword);
        if(byTitleContaining == null) {
            throw new ResourceNotFoundException("No post found with the keyword: " + keyword);
        }
        logger.info("Posts fetched successfully");

        return byTitleContaining.stream().map(post ->
                PostResponseDto.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .addedDate(post.getAddedDate())
                        .imageUrl(post.getImageUrl())
                        .category(post.getCategory())
                        .build()
        ).toList();

    }

    public InputStream downloadImageFromPost(Long postId) throws IOException {
        logger.info("Inside downloadImageFromPost method of PostServiceImpl");
        Post post = postRepository.findById(postId).orElseThrow(() -> new ResourceNotFoundException("Post", "id", postId));
        String imageName = post.getImageUrl();
        String imagePath = Constants.imagePath;
        logger.info("Image name: {} " , imageName);
       return fileService.getResource(imagePath, imageName);
    }


}
