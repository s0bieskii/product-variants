package com.intersport.product.size;

import com.intersport.product.sizecategory.SizeCategory;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "sizes")
public class Size {

    @Id
    @GeneratedValue
    private Long id;
    @Column(name = "sizes")
    private String size;
    @OneToOne
    private SizeCategory sizeCategory;

}
