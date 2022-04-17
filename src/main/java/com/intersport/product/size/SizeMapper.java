package com.intersport.product.size;

import com.intersport.product.size.dto.SizeAddDto;
import com.intersport.product.size.dto.SizeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface SizeMapper {
    SizeMapper SIZE_MAPPER = Mappers.getMapper(SizeMapper.class);

    Size addDtoToSize(SizeAddDto sizeAddDto);

    Size updateDtoToSize(SizeUpdateDto size);

}
