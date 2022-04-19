package com.intersport.product.sizecategory.dto;

import com.intersport.product.size.Size;
import com.intersport.product.size.dto.SizeDto;
import com.intersport.product.size.dto.SizeMapper;
import com.intersport.product.sizecategory.SizeCategory;
import com.intersport.product.sizecategory.SizeCategoryService;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SizeCategoryMapper {
    SizeCategoryMapper SIZE_CATEGORY_MAPPER = Mappers.getMapper(SizeCategoryMapper.class);

    SizeCategory addDtoToSizeCategory(SizeCategoryAddDto sizeCategoryAddDto);

    SizeCategory updateDtoToSize(SizeCategoryUpdateDto size);

    SizeCategoryDto sizeCategoryToDto(SizeCategory size);
}
