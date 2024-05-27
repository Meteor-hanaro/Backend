package com.hana.app.repository.security;

import com.hana.app.data.entity.security.SecurityPrice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SecurityPriceRepository extends JpaRepository<SecurityPrice, Long> {
}
