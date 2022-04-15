package com.intersport.product.product.mapper;

import com.intersport.product.brand.mapper.BrandMapper;
import com.intersport.product.model.mapper.ModelMapper;
import com.intersport.product.product.Product;
import com.intersport.product.product.dto.ProductAddDto;
import com.intersport.product.product.dto.ProductDto;
import com.intersport.product.product.dto.ProductUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {ModelMapper.class, BrandMapper.class})
public interface ProductMapper {
    ProductMapper PRODUCT_MAPPER = Mappers.getMapper(ProductMapper.class);

    ProductDto productToDto(Product product);

    Product addDtoToProduct(ProductAddDto productAddDto);

    Product productUpdate(ProductUpdateDto productUpdate);
}
