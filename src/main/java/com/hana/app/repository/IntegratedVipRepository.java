package com.hana.app.repository;

import com.hana.app.data.entity.IntegratedVip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IntegratedVipRepository extends JpaRepository<IntegratedVip, Long> {
    List<IntegratedVip> findByPbId(Long pbId);

    IntegratedVip findByEmail(String name);
    IntegratedVip findByVipId(Long vipId);
}
