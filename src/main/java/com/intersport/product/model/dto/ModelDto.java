package com.intersport.product.model.dto;

import com.intersport.product.gender.Gender;
import com.intersport.product.type.Type;
import javax.persistence.OneToOne;

public class ModelDto {

    private Long id;
    private String name;
    private Type type;
    private Gender gender;
}
