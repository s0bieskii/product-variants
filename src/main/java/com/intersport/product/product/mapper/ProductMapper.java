package com.intersport.product.product.mapper;

import com.intersport.product.product.Product;
import com.intersport.product.product.dto.ProductAddDto;
import com.intersport.product.product.dto.ProductDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto productToDto(Product product);
    Product addDtoToProduct(ProductAddDto productAddDto);
}
