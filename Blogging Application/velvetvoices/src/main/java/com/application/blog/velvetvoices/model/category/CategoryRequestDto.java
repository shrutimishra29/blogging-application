package com.application.blog.velvetvoices.model.category;

import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CategoryRequestDto {

    @Size(min = 2, max = 20)
    private String categoryTitle;

    @Size(min = 2, max = 20)
    private String categoryName;

    @Size(min = 2, max =1000)
    private String categoryDescription;
}
