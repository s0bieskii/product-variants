package com.intersport.product.gender;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GenderRepository extends JpaRepository<Gender, Long> {

    Optional<Gender> findGenderByNameIgnoreCase(String name);
}
