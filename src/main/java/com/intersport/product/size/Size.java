package com.intersport.product.size;

import com.intersport.product.category.Category;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Size {

    @Id
    @GeneratedValue
    private Long id;
    private String size;
    @OneToOne
    private Category category;

}
