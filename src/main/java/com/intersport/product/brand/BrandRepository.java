package com.intersport.product.brand;

import com.intersport.product.gender.Gender;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BrandRepository extends JpaRepository<Brand, Long> {

    Optional<Brand> findBrandByName(String name);
}
