package com.intersport.product.size.dto;

import com.intersport.product.category.dto.CategoryMapper;
import com.intersport.product.size.Size;
import com.intersport.product.sizecategory.dto.SizeCategoryMapper;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(uses = {CategoryMapper.class, SizeCategoryMapper.class})
public interface SizeMapper {
    SizeMapper SIZE_MAPPER = Mappers.getMapper(SizeMapper.class);

    Size addDtoToSize(SizeAddDto sizeAddDto);

    Size updateDtoToSize(SizeUpdateDto size);

    SizeDto sizeToDto(Size size);

}
