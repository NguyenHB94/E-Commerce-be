package com.tm.j10.repository;

import com.tm.j10.domain.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Ward entity.
 */
@SuppressWarnings("unused")
@Repository
public interface WardRepository extends JpaRepository<Ward, Long> {
    @Query(value = "select w " +
        "from Ward w " +
        "left join w.customers c " +
        "where c.id = :customerId")
    Ward findByCustomerId(@Param("customerId") Long customerId);
}
