package com.intersport.product.brand.dto;

import com.intersport.product.brand.Brand;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper BRAND_MAPPER = Mappers.getMapper(BrandMapper.class);

    BrandDto brandToDto(Brand brand);

    Brand addDtoToBrand(BrandAddDto brandAddDto);

    Brand updateDtoToBrand(BrandUpdateDto brandUpdateDto);
}
