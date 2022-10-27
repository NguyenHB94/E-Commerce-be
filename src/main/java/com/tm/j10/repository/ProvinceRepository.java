package com.tm.j10.repository;

import com.tm.j10.domain.Province;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Province entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ProvinceRepository extends JpaRepository<Province, Long> {
    @Query(value = "select p " +
        "from Province p " +
        "left join p.customers c " +
        "where c.id = :customerId")
    Province findByCustomerId(@Param("customerId") Long customerId);
}
