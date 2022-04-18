package com.intersport.product.product.dto;

import com.intersport.product.brand.dto.BrandMapper;
import com.intersport.product.model.dto.ModelMapper;
import com.intersport.product.product.Product;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ModelMapper.class, BrandMapper.class})
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto productToDto(Product product);

    Product addDtoToProduct(ProductAddDto productAddDto);

    Product productUpdate(ProductUpdateDto productUpdate);
}
