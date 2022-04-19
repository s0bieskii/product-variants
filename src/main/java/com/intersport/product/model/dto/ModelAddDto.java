package com.intersport.product.model.dto;

import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ModelAddDto {

    @NotNull
    private String name;
    @NotNull
    private Long typeId;
    @NotNull
    private Long genderId;
}
