package com.application.blog.velvetvoices.model.category;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryResponseDto {
    private Long categoryId;

    private String categoryName;

    private String categoryDescription;
}
