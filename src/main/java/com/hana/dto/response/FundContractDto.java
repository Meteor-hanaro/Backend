package com.hana.dto.response;

import com.hana.app.data.entity.fund.FundContract;

import lombok.Getter;

@Getter
public class FundContractDto {
	private final Long id;
	private final String title;
	private final String description;
	private final String pdfUrl;

	public FundContractDto(FundContract fundContract) {
		this.id = fundContract.getId();
		this.title = fundContract.getTitle();
		this.description = fundContract.getDescription();
		this.pdfUrl = fundContract.getPdfUrl();
	}

	public static FundContractDto from(FundContract fundContract) {
		return new FundContractDto(fundContract);
	}

}
