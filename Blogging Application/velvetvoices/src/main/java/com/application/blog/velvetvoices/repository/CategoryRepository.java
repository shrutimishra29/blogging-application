package com.application.blog.velvetvoices.repository;

import com.application.blog.velvetvoices.model.category.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    public Category findByCategoryName(String name);
}
