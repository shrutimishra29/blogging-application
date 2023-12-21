package com.application.blog.velvetvoices.controller;

import com.application.blog.velvetvoices.constants.Constants;
import com.application.blog.velvetvoices.model.post.PostRequestDto;
import com.application.blog.velvetvoices.model.post.PostResponseDto;
import com.application.blog.velvetvoices.services.FileService;
import com.application.blog.velvetvoices.services.PostService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.io.InputStream;


import java.util.List;

@RestController
@RequestMapping("/api/v1/posts")
@SecurityRequirement(name = "velvetvoiceapi")
@CrossOrigin(origins = "*")
public class PostController {

    @Autowired
    private PostService postService;

    @Autowired
    private FileService fileService;

    private Logger  logger = org.slf4j.LoggerFactory.getLogger(PostController.class);

    @PostMapping("/add/category/{categoryId}/user/{userId}")
    public PostResponseDto saveNewPost(@RequestBody PostRequestDto postRequestDto, @PathVariable("categoryId") Long categoryId, @PathVariable("userId") Long userId) {
        logger.info("Inside saveNewPost method");
        logger.info("Post Request Dto : {}",postRequestDto);
        return postService.createPost(postRequestDto, categoryId, userId);
    }

    @GetMapping("/{postId}")
    public PostResponseDto getPostById(@PathVariable("postId") Long postId) {
        logger.info("Inside getPostById method");
        logger.info("Post Id : {}",postId);
        return postService.getPost(postId);
    }

    @GetMapping("/category/{categoryId}")
    public List<PostResponseDto> getPostByCategory(@PathVariable("categoryId") Long categoryId) {
        logger.info("Inside getPostByCategory method");
        logger.info("Category Id : {}",categoryId);
        return postService.getPostByCategoryId(categoryId);
    }

    @GetMapping("/user/{userId}")
    public List<PostResponseDto> getPostByUserId(@PathVariable("userId") Long userId) {
        logger.info("Inside getPostByUserId method");
        logger.info("User Id : {}",userId);
        return postService.getPostByUserId(userId);
    }

    @GetMapping("/all")
    public List<PostResponseDto> getAllPost() {
        logger.info("Inside getAllPost method");
        return postService.getAllPost();
    }

    @DeleteMapping("/{postId}")
    public String deletePost(@PathVariable("postId") Long postId) {
        logger.info("Inside deletePost method");
        logger.info("Post Id : {}",postId);
        return postService.deletePost(postId);
    }


    @GetMapping("/search/{keyword}")
    public List<PostResponseDto> searchPost(@PathVariable("keyword") String keyword) {
        logger.info("Inside searchPost method");
        logger.info("Search Keyword : {}", keyword);
        return postService.searchPost(keyword);
    }

    //post Image upload
    @PostMapping("/image/upload/{postId}")
    public ResponseEntity<PostResponseDto> uploadImage(@RequestParam("image") MultipartFile image, @PathVariable("postId") Long postId) throws IOException {

        logger.info("Inside uploadImage method");
        logger.info("Post Id : {}",postId);
        String fileName = this.fileService.uploadImage(Constants.imagePath, image);
        PostResponseDto post = this.postService.getPost(postId);
        PostResponseDto postResponseDto = this.postService.updatePost(
                PostRequestDto.builder()
                        .postId(post.getPostId())
                        .title(post.getTitle())
                        .content(post.getContent())
                        .category(post.getCategory())
                        .imageUrl(fileName)
                        .user(post.getUser())
                        .build(),
                post.getPostId()
        );

        return ResponseEntity.ok(postResponseDto);
    }

    @GetMapping("/image/{imageName}")
    public void downloadImage(@PathVariable("imageName") String imageName
                 , HttpServletResponse response) throws IOException {
        logger.info("Inside downloadImage method");
        logger.info("Image Name : {}",imageName);
        InputStream resource = this.fileService.getResource(Constants.imagePath, imageName);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

    @GetMapping("/image/post/{postId}")
    public void downloadImageFromPost(@PathVariable("postId") Long postId,
                                               HttpServletResponse response) throws IOException {
        logger.info("Inside downloadImageFromPost method");
        logger.info("Post Id : {}",postId);
        InputStream resource = this.postService.downloadImageFromPost(postId);
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(resource, response.getOutputStream());
    }

}
