package com.intersport.product.model;

import com.intersport.product.gender.Gender;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

    Optional<Model> findByGenderId(Long id);

    Optional<Model> findByTypeId(Long name);
}
