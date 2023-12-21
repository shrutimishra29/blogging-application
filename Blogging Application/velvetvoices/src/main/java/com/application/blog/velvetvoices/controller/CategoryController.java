package com.application.blog.velvetvoices.controller;

import com.application.blog.velvetvoices.model.category.CategoryRequestDto;
import com.application.blog.velvetvoices.model.category.CategoryResponseDto;
import com.application.blog.velvetvoices.services.CategoryService;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RestController
@RequestMapping("/api/v1/category")
@SecurityRequirement(name = "velvetvoiceapi")
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    private Logger logger = LoggerFactory.getLogger(CategoryController.class);

    @PostMapping("/add")
    public CategoryResponseDto addCategory(@Valid @RequestBody CategoryRequestDto categoryRequestDto){
        logger.info("Inside addCategory method of CategoryController");
        logger.info("Category Request Dto : {}",categoryRequestDto);
        return categoryService.createCategory(categoryRequestDto);
    }


    @PostMapping("/update/{categoryId}")
    public CategoryResponseDto updateCategory( @PathVariable("categoryId") Long categoryId, @Valid @RequestBody CategoryRequestDto categoryRequestDto){
        logger.info("Inside updateCategory method of CategoryController");
        logger.info("Category Id : {}",categoryId);
        logger.info("Category Request Dto : {}",categoryRequestDto);
        return categoryService.updateCategory(categoryId, categoryRequestDto);
    }

    @GetMapping("/{categoryId}")
    public CategoryResponseDto getCategoryById(@PathVariable("categoryId") Long categoryId){
        logger.info("Inside getCategoryById method of CategoryController");
        logger.info("Category Id : {}",categoryId);
        logger.info("Category Request Dto : {}",categoryId);
        return categoryService.getCategoryById(categoryId);
    }


    @GetMapping("/name/{name}")
    public CategoryResponseDto getCategoryByName(@PathVariable("name") String name){
        logger.info("Inside getCategoryByName method of CategoryController");
        logger.info("Category Name : {}",name);
        return categoryService.getCategoryByName(name);
    }



    @DeleteMapping("/{categoryId}")
    public String deleteCategory(@PathVariable("categoryId") Long categoryId){
        logger.info("Inside deleteCategory method of CategoryController");
        logger.info("Category Id : {}",categoryId);
        return categoryService.deleteCategory(categoryId);
    }

    @GetMapping("/all")
    public List<CategoryResponseDto> getAllCategories(){
        logger.info("Inside getAllCategories method of CategoryController");
        return categoryService.getAllCategories();
    }

    @PostMapping("/save")
    public List<CategoryResponseDto> saveAllCategories(@RequestBody List<CategoryRequestDto> categoryRequestDtoList){
        logger.info("Inside saveAllCategories method of CategoryController");
        logger.info("Category Request Dto List : {}",categoryRequestDtoList);
        return categoryService.saveAllCategories(categoryRequestDtoList);  //<-- this is the method that will be used to save a list of CategoryRequestDto objects to the database and return a list of CategoryResponseDto objects. The method signature is List<CategoryResponseDto> saveAllCategories(List
    }

}
