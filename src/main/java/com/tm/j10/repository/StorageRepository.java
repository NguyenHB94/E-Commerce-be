package com.tm.j10.repository;

import com.tm.j10.domain.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Storage entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StorageRepository extends JpaRepository<Storage, Long> {
    @Query(value = "select s " +
        "from Storage s " +
        "left join s.product p " +
        "left join s.productSize ps " +
        "left join s.color c " +
        "left join s.store st " +
        "where p.id = :productId and ps.id = :productSizeId and c.id = :colorId")
    List<Storage> findByProductIdAndProductSizeIdAndColorId(
        @Param("productId") Long productId,
        @Param("productSizeId") Long productSizeId,
        @Param("colorId") Long colorId
    );

    @Query("select s  from Storage s left join fetch s.product p left join fetch s.color c left join fetch s.productSize ps left join fetch s.store st where p.id = ?1")
    public List<Storage> getProductAndColorAndProductSizeAndStore(Long id);

    @Query("SELECT storage FROM Storage storage WHERE storage.store.id = :storeId " +
        "AND storage.product.id = :productId " +
        "AND storage.productSize.id = :productSizeId " +
        "AND storage.color.id = :colorId")
    Optional<Storage> getByStoreAndProductAndProductSizeAndColor(@Param("storeId") Long storeId,
                                                                 @Param("productId") Long productId,
                                                                 @Param("productSizeId") Long productSizeId,
                                                                 @Param("colorId") Long colorId);
}

