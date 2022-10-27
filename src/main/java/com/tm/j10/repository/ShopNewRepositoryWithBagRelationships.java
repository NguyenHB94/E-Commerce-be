package com.tm.j10.repository;

import com.tm.j10.domain.ShopNew;
import java.util.List;
import java.util.Optional;
import org.springframework.data.domain.Page;

public interface ShopNewRepositoryWithBagRelationships {
    Optional<ShopNew> fetchBagRelationships(Optional<ShopNew> shopNew);

    List<ShopNew> fetchBagRelationships(List<ShopNew> shopNews);

    Page<ShopNew> fetchBagRelationships(Page<ShopNew> shopNews);
}
