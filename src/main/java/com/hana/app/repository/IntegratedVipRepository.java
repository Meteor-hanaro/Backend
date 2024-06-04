package com.hana.app.repository;

import com.hana.app.data.entity.IntegratedVip;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface IntegratedVipRepository extends JpaRepository<IntegratedVip, Long> {
    List<IntegratedVip> findByPbId(Long pbId);

    IntegratedVip findByEmail(String name);

    @Query("SELECT v FROM IntegratedVip v WHERE v.pbId = :pbId AND v.riskType = :riskType AND v.name LIKE %:name%")
    List<IntegratedVip> findByPbIdAndRiskTypeAndName(Long pbId, String riskType, String name);

    @Query("SELECT v FROM IntegratedVip v WHERE v.pbId = :pbId AND v.name LIKE %:name%")
    List<IntegratedVip> findByPbIdAndName(Long pbId, String name);
}
