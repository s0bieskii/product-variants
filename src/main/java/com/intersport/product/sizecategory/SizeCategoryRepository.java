package com.intersport.product.sizecategory;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeCategoryRepository extends JpaRepository<SizeCategory, Long> {

    List<SizeCategory> findByNameIgnoreCase(String name);
}
