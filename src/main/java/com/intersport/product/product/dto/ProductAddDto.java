package com.intersport.product.product.dto;

import com.intersport.product.brand.dto.BrandAddDto;
import com.intersport.product.model.dto.ModelAddDto;

public record ProductAddDto(ModelAddDto model, BrandAddDto brand) {
}