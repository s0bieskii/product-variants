package com.intersport.product.product;

import com.intersport.product.brand.Brand;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Optional<Brand> findModelByBrandId(Long id);
}
