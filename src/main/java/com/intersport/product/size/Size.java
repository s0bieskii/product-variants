package com.intersport.product.size;

import com.intersport.product.category.Category;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Size {

    @Id
    @GeneratedValue
    private Long id;
    private String size;
    @OneToOne
    private Category category;
}
