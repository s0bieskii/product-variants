package com.intersport.product.model;

import com.intersport.product.gender.GenderMapper;
import com.intersport.product.model.dto.ModelAddDto;
import com.intersport.product.model.dto.ModelDto;
import com.intersport.product.model.dto.ModelUpdateDto;
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
