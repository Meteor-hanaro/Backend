package com.hana.app.repository;

import com.hana.app.data.entity.IntegratedPb;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IntegratedPbRepository extends JpaRepository<IntegratedPb, Long> {
    IntegratedPb findByEmail(String name);

    IntegratedPb findByPbId(Long pbId);
}
