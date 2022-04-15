package com.intersport.product.model.mapper;

import com.intersport.product.gender.mapper.GenderMapper;
import com.intersport.product.model.Model;
import com.intersport.product.model.dto.ModelAddDto;
import com.intersport.product.model.dto.ModelDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import org.springframework.data.convert.TypeMapper;

@Mapper(uses = {TypeMapper.class, GenderMapper.class})
public interface ModelMapper {
    ModelMapper MODEL_MAPPER = Mappers.getMapper(ModelMapper.class);

    Model addDtoToModel(ModelAddDto modelAddDto);

    ModelDto modelToDto(Model model);
}