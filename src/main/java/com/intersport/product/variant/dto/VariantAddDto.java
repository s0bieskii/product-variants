package com.intersport.product.variant.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VariantAddDto {

    private String color;
    private Long modelId;
    private Long sizeId;
    private BigDecimal price;

}
