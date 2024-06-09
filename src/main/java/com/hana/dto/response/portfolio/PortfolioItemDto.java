package com.hana.dto.response.portfolio;

import com.hana.app.data.entity.portfolio.PortfolioItem;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class PortfolioItemDto {
    private final Long itemId;
    private final Long fundId;
    private final String fundName;
    private final Long startAmount;
    private final Long evaluationAmount;
    private final LocalDateTime createdAt;

    public PortfolioItemDto(PortfolioItem portfolioItem) {
        this.itemId = portfolioItem.getId();
        this.fundId = portfolioItem.getFund().getId();
        this.fundName = portfolioItem.getFund().getName();
        this.startAmount = portfolioItem.getStartAmount();
        this.evaluationAmount = portfolioItem.getEvaluationAmount();
        this.createdAt = portfolioItem.getCreatedAt();
    }

    public static PortfolioItemDto from(PortfolioItem portfolioItem) {
        return new PortfolioItemDto(portfolioItem);
    }
}