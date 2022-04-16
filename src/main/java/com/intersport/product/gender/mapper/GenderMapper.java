package com.intersport.product.gender.mapper;

import com.intersport.product.gender.Gender;
import com.intersport.product.gender.dto.GenderAddDto;
import com.intersport.product.gender.dto.GenderUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenderMapper {
    GenderMapper Gender_MAPPER = Mappers.getMapper(GenderMapper.class);

    Gender addDtoToGender(GenderAddDto gender);
    Gender updateDtoToGender(GenderUpdateDto genderUpdateDto);
}
