package com.intersport.product.size;

import com.intersport.product.sizecategory.SizeCategory;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
public class Size {

    @Id
    @GeneratedValue
    private Long id;
    private String size;
    @OneToOne
    private SizeCategory sizeCategory;

}
