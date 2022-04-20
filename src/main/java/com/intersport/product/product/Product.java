package com.intersport.product.product;

import com.intersport.product.brand.Brand;
import com.intersport.product.category.Category;
import com.intersport.product.model.Model;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
//@NoArgsConstructor
//@ToString
@Table(name = "PRODUCTS")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    private Model model;
    @OneToOne
    private Brand brand;
    @OneToOne
    private Category category;
    private boolean archived;

}
