package com.tm.j10.repository;

import com.tm.j10.domain.ProductSize;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ProductSize entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProductSizeRepository extends JpaRepository<ProductSize, Long> {}
