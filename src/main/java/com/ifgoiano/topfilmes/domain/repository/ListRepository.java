package com.ifgoiano.topfilmes.domain.repository;

import com.ifgoiano.topfilmes.domain.model.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListRepository extends JpaRepository<List, Long> {
}
