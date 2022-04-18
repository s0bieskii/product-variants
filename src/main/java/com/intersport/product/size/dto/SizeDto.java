package com.intersport.product.size.dto;

import com.intersport.product.category.dto.CategoryDto;

public record SizeDto(Long id, String size, CategoryDto category) {
}
