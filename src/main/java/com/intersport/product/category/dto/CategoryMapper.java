package com.intersport.product.category.dto;

import com.intersport.product.category.Category;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    Category addDtoToCategory(CategoryAddDto categoryAddDto);

    Category updateDtoToCategory(CategoryUpdateDto categoryUpdateDto);

    CategoryDto categoryToDto(Category category);
}
