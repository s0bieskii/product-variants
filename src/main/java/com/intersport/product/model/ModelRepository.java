package com.intersport.product.model;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ModelRepository extends JpaRepository<Model, Long> {

    List<Model> findByGenderId(Long id);

    List<Model> findByTypeId(Long name);
}
