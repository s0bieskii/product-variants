package com.intersport.product.brand.mapper;

import com.intersport.product.brand.Brand;
import com.intersport.product.brand.dto.BrandAddDto;
import com.intersport.product.brand.dto.BrandDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper BRAND_MAPPER = Mappers.getMapper(BrandMapper.class);

    BrandDto brandToDto(Brand brand);

    Brand addDtoToProduct(BrandAddDto brandAddDto);
}