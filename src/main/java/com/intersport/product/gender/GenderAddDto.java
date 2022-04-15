package com.intersport.product.gender;

import lombok.Getter;
import lombok.Setter;
import org.springframework.web.bind.annotation.GetMapping;

public record GenderAddDto(String name) {

}
