package com.intersport.product.size;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SizeRepository extends JpaRepository<Size, Long> {

    Optional<Size> findBySizeAndCategoryId(String size, Long categoryId);
}
