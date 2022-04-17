package com.intersport.product.type;

import com.intersport.product.type.dto.TypeAddDto;
import com.intersport.product.type.dto.TypeUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeMapper {
    TypeMapper MODEL_MAPPER = Mappers.getMapper(TypeMapper.class);

    Type addDtoToType(TypeAddDto type);

    Type updateDtoToType(TypeUpdateDto typeUpdateDto);
}
