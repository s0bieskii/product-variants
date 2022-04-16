package com.intersport.product.gender.brand;

import com.intersport.product.gender.brand.dto.BrandAddDto;
import com.intersport.product.gender.brand.dto.BrandDto;
import com.intersport.product.gender.brand.dto.BrandUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper BRAND_MAPPER = Mappers.getMapper(BrandMapper.class);

    BrandDto brandToDto(Brand brand);

    Brand addDtoToBrand(BrandAddDto brandAddDto);

    Brand updateDtoToBrand(BrandUpdateDto brandUpdateDto);
}
