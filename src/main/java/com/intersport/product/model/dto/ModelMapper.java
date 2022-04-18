package com.intersport.product.model.dto;

import com.intersport.product.gender.dto.GenderMapper;
import com.intersport.product.model.Model;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.convert.TypeMapper;

@Mapper(uses = {TypeMapper.class, GenderMapper.class})
public interface ModelMapper {
    ModelMapper MODEL_MAPPER = Mappers.getMapper(ModelMapper.class);

    Model addDtoToModel(ModelAddDto modelAddDto);

    Model updateDtoToModel(ModelUpdateDto modelUpdateDto);

    ModelDto modelToDto(Model model);
}
