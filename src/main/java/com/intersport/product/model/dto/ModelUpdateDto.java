package com.intersport.product.model.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModelUpdateDto {

    private Long id;
    private String name;
    private Long typeId;
    private Long genderId;
}
