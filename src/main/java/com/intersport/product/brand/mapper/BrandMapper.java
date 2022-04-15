package com.intersport.product.brand.mapper;

import com.intersport.product.brand.Brand;
import com.intersport.product.brand.dto.BrandAddDto;
import com.intersport.product.brand.dto.BrandDto;
import com.intersport.product.product.Product;
import com.intersport.product.product.dto.ProductAddDto;
import com.intersport.product.product.dto.ProductDto;
import com.intersport.product.product.mapper.ProductMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface BrandMapper {
    BrandMapper BRAND_MAPPER = Mappers.getMapper(BrandMapper.class);

    BrandDto brandToDto(Brand brand);
    Brand addDtoToProduct(BrandAddDto brandAddDto);
}
