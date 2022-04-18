package com.intersport.product.variant.dto;

import com.intersport.product.model.dto.ModelDto;
import com.intersport.product.size.Size;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VariantDto {

    private Long id;
    private ModelDto model;
    private String color;
    private Size size;
    private BigDecimal price;
}
