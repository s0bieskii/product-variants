package com.intersport.product.model.dto;

import com.intersport.product.type.dto.TypeAddDto;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModelAddDto {

    private String name;
    private TypeAddDto type;
    private ModelAddDto gender;
}
