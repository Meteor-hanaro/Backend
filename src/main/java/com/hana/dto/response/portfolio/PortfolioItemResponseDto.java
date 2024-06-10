package com.hana.dto.response.portfolio;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PortfolioItemResponseDto {
    private final String fundName;
    private final Long startAmount;
    private final LocalDateTime startDate;
    private final Long evaluationAmount;

    public PortfolioItemResponseDto(String name, Long startAmount, LocalDateTime startDate, Long evaluationAmount) {
        this.fundName = name;
        this.startAmount = startAmount;
        this.startDate = startDate;
        this.evaluationAmount = evaluationAmount;
    }

    public static PortfolioItemResponseDto from(String name, Long startAmount, LocalDateTime createdAt, Long evaluationAmount) {
        return new PortfolioItemResponseDto(name, startAmount, createdAt, evaluationAmount);
    }
}
