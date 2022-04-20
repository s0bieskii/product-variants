package com.intersport.product.gender;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Getter
@Setter
@ToString
@Table(name = "genders")
public class Gender {

    @Id
    @GeneratedValue
    private Long id;
    private String name;
}
