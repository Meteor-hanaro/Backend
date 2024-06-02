package com.hana.app.repository.security;

import com.hana.app.data.entity.security.SecurityPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface SecurityPriceRepository extends JpaRepository<SecurityPrice, String> {
    List<SecurityPrice> findBySecurityId(String securityId);
    List<SecurityPrice> findBySecurityIdAndTradeDateBetween(String securityId, LocalDateTime startDate, LocalDateTime endDate);
    SecurityPrice findSecurityPriceByTradeDateAndSecurityId(LocalDateTime tradeDate, String securityId);
}
