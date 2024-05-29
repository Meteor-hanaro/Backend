package com.hana.app.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hana.app.data.entity.fund.FundContract;
import com.hana.app.repository.fund.FundContractRepository;
import com.hana.dto.response.FundContractDto;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class FundContractService {

	private final FundContractRepository fundContractRepository;

	public List<FundContractDto> getFundJoinContractsByFundId(Long fundId) {
		List<FundContract> fundContracts = fundContractRepository.findByFundId(fundId);
		return fundContracts.stream()
			.filter(fundContract -> fundContract.getContractType().equals(FundContract.ContractType.JOIN))
			.map(FundContractDto::from)
			.collect(Collectors.toList());
	}
}
