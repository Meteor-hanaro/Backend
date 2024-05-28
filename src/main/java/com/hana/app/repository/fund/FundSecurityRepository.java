package com.hana.app.repository.fund;

import com.hana.app.data.entity.fund.FundSecurity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FundSecurityRepository extends JpaRepository<FundSecurity, Long> {
    List<FundSecurity> findFundSecuritiesByFund_IdOrderByFundSecurityPercentageDesc(Long fund_id);
}
