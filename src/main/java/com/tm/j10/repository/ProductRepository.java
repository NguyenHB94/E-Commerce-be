package com.tm.j10.repository;

import com.tm.j10.domain.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data JPA repository for the Product entity.
 *
 * When extending this class, extend ProductRepositoryWithBagRelationships too.
 * For more information refer to https://github.com/jhipster/generator-jhipster/issues/17990.
 */
@Repository
public interface ProductRepository extends ProductRepositoryWithBagRelationships, JpaRepository<Product, Long> {
    default Optional<Product> findOneWithEagerRelationships(Long id) {
        return this.fetchBagRelationships(this.findOneWithToOneRelationships(id));
    }

    default List<Product> findAllWithEagerRelationships() {
        return this.fetchBagRelationships(this.findAllWithToOneRelationships());
    }

    default Page<Product> findAllWithEagerRelationships(Pageable pageable) {
        return this.fetchBagRelationships(this.findAllWithToOneRelationships(pageable));
    }

    @Query(
        value = "select distinct product from Product product left join fetch product.category",
        countQuery = "select count(distinct product) from Product product"
    )
    Page<Product> findAllWithToOneRelationships(Pageable pageable);

    @Query("select distinct product from Product product left join fetch product.category")
    List<Product> findAllWithToOneRelationships();

    @Query("select product from Product product left join fetch product.category where product.id =:id")
    Optional<Product> findOneWithToOneRelationships(@Param("id") Long id);

    @Query("select product from Product product where product.category.id = ?1 and product.isValid = ?2")
    Page<Product> findByCategoryAndIsValid(Long categoryId, Boolean isValid, Pageable pageable);

    Optional<Product> findProductByIdAndIsEnableAndIsValid(Long id, Boolean isEnable, Boolean isValid);

    List<Product> getByCategoryCategoryNameAndIsEnable(String name, Boolean isEnable, Pageable pageable);

    @Query("SELECT p FROM Product p WHERE p.name LIKE %?1%")
    public List<Product> search(String keyword, Pageable pageable);

    Optional<Product> findByIdAndIsValidAndIsEnable(Long productId, Boolean isvalid, Boolean isEnable);
}
