package com.intersport.product.type.mapper;

import com.intersport.product.type.Type;
import com.intersport.product.type.dto.TypeAddDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface TypeMapper {
    TypeMapper MODEL_MAPPER = Mappers.getMapper(TypeMapper.class);

    Type addDtoToType(TypeAddDto type);
}
