package com.hana.dto.response;

import com.hana.app.data.entity.portfolio.PortfolioItem;
import lombok.Getter;

@Getter
public class PortfolioItemDto {
    private final Long fundId;
    private final Long startAmount;
    private final Long evaluationAmount;

    public PortfolioItemDto(PortfolioItem portfolioItem) {
        this.fundId = portfolioItem.getFund().getId();
        this.startAmount = portfolioItem.getStartAmount();
        this.evaluationAmount = portfolioItem.getEvaluationAmount();
    }

    public static PortfolioItemDto from(PortfolioItem portfolioItem) {
        return new PortfolioItemDto(portfolioItem);
    }
}