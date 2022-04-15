package com.intersport.product.gender;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Gender {

    @Id
    @GeneratedValue
    private Long id;
    private String gender;
}
