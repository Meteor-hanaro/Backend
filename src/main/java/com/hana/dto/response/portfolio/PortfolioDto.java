package com.hana.dto.response.portfolio;

import com.hana.app.data.entity.portfolio.Portfolio;
import lombok.Getter;

import java.util.List;

@Getter
public class PortfolioDto {
    private final Long id;
    private final String userName;
    private final Long totalValue;
    private final List<PortfolioItemDto> portfolioItems;

    public PortfolioDto(Portfolio portfolio, List<PortfolioItemDto> portfolioItems) {
        this.id = portfolio.getId();
        this.userName = portfolio.getVip().getUser().getName();
        this.totalValue = portfolio.getTotalValue();
        this.portfolioItems = portfolioItems;
    }

    public static PortfolioDto from(Portfolio portfolio, List<PortfolioItemDto> portfolioItems) {
        return new PortfolioDto(portfolio, portfolioItems);
    }
}
