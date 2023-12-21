package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.model.category.CategoryRequestDto;
import com.application.blog.velvetvoices.model.category.CategoryResponseDto;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface CategoryService {

    public CategoryResponseDto  getCategoryById(Long categoryId);

    public CategoryResponseDto  getCategoryByName(String name);

    public CategoryResponseDto  createCategory(CategoryRequestDto categoryRequestDto);

    public CategoryResponseDto  updateCategory(Long categoryId, CategoryRequestDto categoryRequestDto);

    public String deleteCategory(Long id);

    public List<CategoryResponseDto> getAllCategories(); //<-- this is the method that will be used to retrieve all categories from the database and return them as a list of CategoryResponseDto objects. The method signature is List<CategoryResponseDto> getAllCategories() and it is defined in the CategoryService interface. The method returns a list of

    public List<CategoryResponseDto> saveAllCategories(List<CategoryRequestDto> categoryRequestDtoList); //<-- this is the method that will be used to save a list of CategoryRequestDto objects to the database and return a list of CategoryResponseDto objects. The method signature is List<CategoryResponseDto> saveAllCategories(List

    public List<CategoryResponseDto> getCategoriesByUserId(Long userId);
    public List<CategoryResponseDto> getCategoriesByPostId(Long postId);

}
