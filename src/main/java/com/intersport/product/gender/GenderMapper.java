package com.intersport.product.gender;

import com.intersport.product.gender.dto.GenderAddDto;
import com.intersport.product.gender.dto.GenderUpdateDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenderMapper {
    GenderMapper GENDER_MAPPER = Mappers.getMapper(GenderMapper.class);

    Gender addDtoToGender(GenderAddDto gender);

    Gender updateDtoToGender(GenderUpdateDto genderUpdateDto);
}
