package com.hana.app.repository.fund;

import java.util.List;

import com.hana.app.data.entity.fund.Fund;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FundRepository extends JpaRepository<Fund, Long> {

	List<Fund> findByIdIn(List<Long> ids);
}
