package com.tm.j10.repository;

import com.tm.j10.domain.OrderDesc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Spring Data JPA repository for the OrderDesc entity.
 */
@SuppressWarnings("unused")
@Repository
public interface OrderDescRepository extends JpaRepository<OrderDesc, Long> {
    @Query(value = "select sum(o.count) " +
        "from OrderDesc o " +
        "left join o.storage st " +
        "left join o.shopOrder so " +
        "where st.id = :storageId and (so.orderStatus = 'CREATED' or so.orderStatus = 'PROCESS')")
    Optional<Long> getQuantityProductWaitComplete(@Param("storageId") Long storageID);

}
