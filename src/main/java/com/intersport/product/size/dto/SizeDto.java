package com.intersport.product.size.dto;

import com.intersport.product.sizecategory.dto.SizeCategoryDto;

public record SizeDto(Long id, String size, SizeCategoryDto sizeCategory) {
}
