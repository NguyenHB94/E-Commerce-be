package com.tm.j10.repository;

import com.tm.j10.domain.Stack;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.Repository;

/**
 * Spring Data JPA repository for the Stack entity.
 */
@SuppressWarnings("unused")
@Repository
public interface StackRepository extends JpaRepository<Stack, Long> {}
