package com.intersport.product.product.dto;

import com.intersport.product.brand.dto.BrandDto;
import com.intersport.product.model.dto.ModelDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductDto {

    private Long id;
    private ModelDto model;
    private BrandDto brand;
}
