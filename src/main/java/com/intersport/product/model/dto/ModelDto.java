package com.intersport.product.model.dto;

import com.intersport.product.gender.dto.GenderDto;
import com.intersport.product.type.dto.TypeDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModelDto {

    private Long id;
    private String name;
    private TypeDto type;
    private GenderDto gender;
}
