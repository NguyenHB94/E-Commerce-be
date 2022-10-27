package com.tm.j10.repository;

import com.tm.j10.domain.Media;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Media entity.
 */
@SuppressWarnings("unused")
@Repository
public interface MediaRepository extends JpaRepository<Media, Long> {}
