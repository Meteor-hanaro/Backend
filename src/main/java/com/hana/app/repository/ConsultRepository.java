package com.hana.app.repository;

import com.hana.app.data.entity.Consult;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultRepository extends JpaRepository<Consult, Long> {
    @Query("SELECT MAX(c.startedAt) FROM Consult c WHERE c.vip.id = :vipId")
    LocalDateTime findMaxStartedAtByVipId(@Param("vipId") Long vipId);
}
