package com.intersport.product.variant.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VariantUpdateDto {

    private Long id;
    private Long modelId;
    private String color;
    private Long sizeId;
    private BigDecimal price;
}
