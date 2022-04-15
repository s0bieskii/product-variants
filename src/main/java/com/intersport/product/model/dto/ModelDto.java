package com.intersport.product.model.dto;

import com.intersport.product.gender.Gender;
import com.intersport.product.type.Type;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModelDto {

    private Long id;
    private String name;
    private Type type;
    private Gender gender;
}
