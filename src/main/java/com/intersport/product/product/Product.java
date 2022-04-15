package com.intersport.product.product;

import com.intersport.product.brand.Brand;
import com.intersport.product.model.Model;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Model model;
    @OneToOne
    private Brand brand;

}
