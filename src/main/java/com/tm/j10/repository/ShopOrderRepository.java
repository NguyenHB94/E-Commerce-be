package com.tm.j10.repository;

import com.tm.j10.domain.ShopOrder;
import com.tm.j10.domain.enumeration.OrderStatus;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the ShopOrder entity.
 */
@SuppressWarnings("unused")
@Repository
public interface ShopOrderRepository extends JpaRepository<ShopOrder, Long> {
    Page<ShopOrder> findByCustomerId(Long customerId, Pageable pageable);

    Page<ShopOrder> findByCustomerIdAndOrderStatus(Long customerId, OrderStatus orderStatus, Pageable pageable);

    Page<ShopOrder> findAllByOrderStatus(OrderStatus orderStatus, Pageable pageable);
}
