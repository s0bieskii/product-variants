package com.intersport.product.variant;

import com.intersport.product.model.Model;
import com.intersport.product.size.Size;
import java.math.BigDecimal;
import java.util.HashSet;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Variant {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Model model;
    private String color;
    @OneToOne
    private Size size;
    private BigDecimal price;

}
