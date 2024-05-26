package com.hana.app.repository.fund;

import com.hana.app.data.entity.fund.FundSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundSecurityRepository extends JpaRepository<FundSecurity, Long> {
}
