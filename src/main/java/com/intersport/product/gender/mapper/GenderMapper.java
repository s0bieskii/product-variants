package com.intersport.product.gender.mapper;

import com.intersport.product.gender.Gender;
import com.intersport.product.gender.GenderAddDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface GenderMapper {
    GenderMapper Gender_MAPPER = Mappers.getMapper(GenderMapper.class);

    Gender genderAddDtoToGender(GenderAddDto gender);
}
