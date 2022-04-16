package com.intersport.product.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ModelAddDto {

    private String name;
    private Long typeId;
    private Long genderId;
}
