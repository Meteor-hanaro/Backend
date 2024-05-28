package com.hana.app.repository.fund;

import java.util.List;

import com.hana.app.data.entity.fund.FundContract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundContractRepository extends JpaRepository<FundContract, Long> {

	List<FundContract> findByFundId(Long fundId);
}
