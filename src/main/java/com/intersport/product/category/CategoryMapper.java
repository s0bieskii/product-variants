package com.intersport.product.category;

import com.intersport.product.category.dto.CategoryAddDto;
import com.intersport.product.category.dto.CategoryUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CategoryMapper {
    CategoryMapper CATEGORY_MAPPER = Mappers.getMapper(CategoryMapper.class);

    Category addDtoToCategory(CategoryAddDto categoryAddDto);

    Category updateDtoToCategory(CategoryUpdateDto categoryUpdateDto);
}
