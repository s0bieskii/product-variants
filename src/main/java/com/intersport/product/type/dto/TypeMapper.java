package com.intersport.product.type.dto;

import com.intersport.product.type.Type;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeMapper {
    TypeMapper MODEL_MAPPER = Mappers.getMapper(TypeMapper.class);

    Type addDtoToType(TypeAddDto type);

    Type updateDtoToType(TypeUpdateDto typeUpdateDto);

    TypeDto typeToDto(Type type);
}
