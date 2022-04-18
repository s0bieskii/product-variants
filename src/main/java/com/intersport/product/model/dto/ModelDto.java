package com.intersport.product.model.dto;

import com.intersport.product.gender.Gender;
import com.intersport.product.gender.dto.GenderDto;
import com.intersport.product.type.Type;
import com.intersport.product.type.dto.TypeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModelDto {

    private Long id;
    private String name;
    private TypeDto type;
    private GenderDto gender;
}
