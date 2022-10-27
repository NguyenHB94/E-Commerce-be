package com.tm.j10.repository;

import com.tm.j10.domain.District;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the District entity.
 */
@SuppressWarnings("unused")
@Repository
public interface DistrictRepository extends JpaRepository<District, Long> {
    @Query(value = "select d " +
        "from District d " +
        "left join d.customers c " +
        "where c.id = :customerId")
    District findByCustomerId(@Param("customerId") Long customerId);
}
