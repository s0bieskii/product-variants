package com.intersport.product.size;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Size {

    @Id
    @GeneratedValue
    private Long id;
    private String size;

}
