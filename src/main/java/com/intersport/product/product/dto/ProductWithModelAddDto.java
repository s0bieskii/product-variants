package com.intersport.product.product.dto;

import com.intersport.product.model.dto.ModelAddDto;
import javax.validation.constraints.NotNull;

public record ProductWithModelAddDto(@NotNull ModelAddDto model, @NotNull Long brandId, @NotNull Long categoryId) {
}
