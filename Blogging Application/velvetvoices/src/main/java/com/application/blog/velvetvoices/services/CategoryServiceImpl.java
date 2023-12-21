package com.application.blog.velvetvoices.services;

import com.application.blog.velvetvoices.exceptions.ResourceNotFoundException;
import com.application.blog.velvetvoices.model.category.Category;
import com.application.blog.velvetvoices.model.category.CategoryRequestDto;
import com.application.blog.velvetvoices.model.category.CategoryResponseDto;
import com.application.blog.velvetvoices.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService{

    @Autowired
    private CategoryRepository categoryRepository;

    private Logger logger = LoggerFactory.getLogger(CategoryServiceImpl.class);

    @Override
    public CategoryResponseDto getCategoryById(Long categoryId) {
        logger.info("Inside getCategoryById method of CategoryServiceImpl");
        logger.info("Category Id : {}",categoryId);
        logger.info("Category Response Dto : {}",getCategoryById(categoryId));
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException(" Category ", "id", categoryId));
        if(category == null) {
            logger.error("Category with id {} not found", categoryId);
        }
        return CategoryResponseDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }

    @Override
    public CategoryResponseDto getCategoryByName(String name) {
        logger.info("Inside getCategoryByName method of CategoryServiceImpl");
        logger.info("Category Name : {}",name);
        Category category = categoryRepository.findByCategoryName(name);
        if(category == null) {
            logger.error("Category with name {} not found", name);
            throw new ResourceNotFoundException("Category with name " + name + " not found");
        }

        logger.info("Category Response Dto : {}",getCategoryByName(name));
        return CategoryResponseDto.builder()
                .categoryId(category.getCategoryId())
                .categoryName(category.getCategoryName())
                .categoryDescription(category.getCategoryDescription())
                .build();
    }

    @Override
    public CategoryResponseDto createCategory(CategoryRequestDto categoryRequestDto) {
        logger.info("Inside createCategory method of CategoryServiceImpl");
        logger.info("Category Request Dto : {}",categoryRequestDto);
        logger.info("Category : {}",categoryRequestDto.getCategoryName());
        Category savedCategory = categoryRepository.save(
                Category.builder()
                        .categoryName(categoryRequestDto.getCategoryName())
                        .categoryDescription(categoryRequestDto.getCategoryDescription())
                        .build()
        );
        logger.info("Category Response Dto : {}",createCategory(categoryRequestDto));
        return CategoryResponseDto.builder()
                .categoryId(savedCategory.getCategoryId())
                .categoryName(savedCategory.getCategoryName())
                .categoryDescription(savedCategory.getCategoryDescription())
                .build();
    }

    @Override
    public CategoryResponseDto updateCategory(Long id, CategoryRequestDto categoryRequestDto) {
        Optional<Category> category = categoryRepository.findById(id);
        if(category.isEmpty()) {
            logger.error("Category with id {} not found", id);
            throw new ResourceNotFoundException("Category with id " + id + " not found");
        }
        Category updatedCategory = categoryRepository.save(
                Category.builder()
                        .categoryId(category.get().getCategoryId())
                        .categoryName(categoryRequestDto.getCategoryName())
                        .categoryDescription(categoryRequestDto.getCategoryDescription())
                        .build()
        );
        return CategoryResponseDto.builder()
                .categoryId(updatedCategory.getCategoryId())
                .categoryName(updatedCategory.getCategoryName())
                .categoryDescription(updatedCategory.getCategoryDescription())
                .build();
    }

    @Override
    public String deleteCategory(Long id) {
        logger.info("Inside deleteCategory method of CategoryServiceImpl");
        logger.info("Category Id : {}",id);
        Category category = categoryRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Category", "id", id));
        categoryRepository.delete(category);
        logger.info("Category with id {} deleted successfully", id);
        return "Category with id " + id + " deleted successfully";
    }

    @Override
    public List<CategoryResponseDto> getAllCategories() {
        logger.info("Inside getAllCategories method of CategoryServiceImpl");
        List<Category> allCategories = categoryRepository.findAll();
        List<CategoryResponseDto> categoryResponseDtos = new java.util.ArrayList<>();

        for(Category category : allCategories) {
            categoryResponseDtos.add(CategoryResponseDto.builder()
                    .categoryId(category.getCategoryId())
                    .categoryName(category.getCategoryName())
                    .categoryDescription(category.getCategoryDescription())
                    .build());
        }

        return categoryResponseDtos;
    }

    @Override
    public List<CategoryResponseDto> saveAllCategories(List<CategoryRequestDto> categoryRequestDtoList) {
        logger.info("Inside saveAllCategories method of CategoryServiceImpl");
        List<Category> categories = new java.util.ArrayList<>();
        for (CategoryRequestDto categoryRequestDto : categoryRequestDtoList) {
            categories.add(Category.builder()
                    .categoryName(categoryRequestDto.getCategoryName())
                    .categoryDescription(categoryRequestDto.getCategoryDescription())
                    .build());
        }

        List<Category> savedCategories = categoryRepository.saveAll(categories);
        List<CategoryResponseDto> categoryResponseDtos = new java.util.ArrayList<>();

        for (Category category : savedCategories) {
            categoryResponseDtos.add(CategoryResponseDto.builder()
                    .categoryId(category.getCategoryId())
                    .categoryName(category.getCategoryName())
                    .categoryDescription(category.getCategoryDescription())
                    .build());
        }

        return categoryResponseDtos;
    }

    @Override
    public List<CategoryResponseDto> getCategoriesByUserId(Long userId) {

        return null;
    }

    @Override
    public List<CategoryResponseDto> getCategoriesByPostId(Long postId) {
        return null;
    }
}
