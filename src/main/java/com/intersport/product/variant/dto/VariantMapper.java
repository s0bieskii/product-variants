package com.intersport.product.variant.dto;

import com.intersport.product.variant.Variant;
import org.mapstruct.Mapper;

@Mapper
public interface VariantMapper {

    VariantDto variantToDto(Variant variant);

    Variant addDtoToVariant(VariantAddDto variantAddDto);

    Variant updateDtoToVariant(VariantUpdateDto variantUpdateDto);
}
