package com.tm.j10.repository;

import com.tm.j10.domain.ShopNew;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.hibernate.annotations.QueryHints;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;

/**
 * Utility repository to load bag relationships based on https://vladmihalcea.com/hibernate-multiplebagfetchexception/
 */
public class ShopNewRepositoryWithBagRelationshipsImpl implements ShopNewRepositoryWithBagRelationships {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public Optional<ShopNew> fetchBagRelationships(Optional<ShopNew> shopNew) {
        return shopNew.map(this::fetchTags);
    }

    @Override
    public Page<ShopNew> fetchBagRelationships(Page<ShopNew> shopNews) {
        return new PageImpl<>(fetchBagRelationships(shopNews.getContent()), shopNews.getPageable(), shopNews.getTotalElements());
    }

    @Override
    public List<ShopNew> fetchBagRelationships(List<ShopNew> shopNews) {
        return Optional.of(shopNews).map(this::fetchTags).orElse(Collections.emptyList());
    }

    ShopNew fetchTags(ShopNew result) {
        return entityManager
            .createQuery("select shopNew from ShopNew shopNew left join fetch shopNew.tags where shopNew is :shopNew", ShopNew.class)
            .setParameter("shopNew", result)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getSingleResult();
    }

    List<ShopNew> fetchTags(List<ShopNew> shopNews) {
        HashMap<Object, Integer> order = new HashMap<>();
        IntStream.range(0, shopNews.size()).forEach(index -> order.put(shopNews.get(index).getId(), index));
        List<ShopNew> result = entityManager
            .createQuery(
                "select distinct shopNew from ShopNew shopNew left join fetch shopNew.tags where shopNew in :shopNews",
                ShopNew.class
            )
            .setParameter("shopNews", shopNews)
            .setHint(QueryHints.PASS_DISTINCT_THROUGH, false)
            .getResultList();
        Collections.sort(result, (o1, o2) -> Integer.compare(order.get(o1.getId()), order.get(o2.getId())));
        return result;
    }
}
