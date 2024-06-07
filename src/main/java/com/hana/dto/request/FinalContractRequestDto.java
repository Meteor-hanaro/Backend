package com.hana.dto.request;

import java.util.List;

import lombok.Getter;

@Getter
public class FinalContractRequestDto {
	private Long suggestionId;
	private Long vipId;
	private Long pbId;
	private List<ContractRequestDto> contracts;
}
