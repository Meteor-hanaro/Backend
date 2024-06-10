package com.hana.dto.response.fund;

import java.util.List;

import com.hana.app.data.entity.fund.Fund;

import lombok.Getter;

@Getter
public class FundContractsResponseDto {

	private Long fundId;
	private String fundTitle;
	private List<FundContractDto> fundContracts;

	public FundContractsResponseDto(Fund fund, List<FundContractDto> fundContracts) {
		this.fundId = fund.getId();
		this.fundTitle = fund.getName();
		this.fundContracts = fundContracts;
	}

	public static FundContractsResponseDto from(Fund fund, List<FundContractDto> fundContracts) {
		return new FundContractsResponseDto(fund, fundContracts);
	}

}
