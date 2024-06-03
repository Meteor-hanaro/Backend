package com.hana.dto.response;

import com.hana.app.data.entity.portfolio.Portfolio;
import lombok.Getter;

import java.util.List;

@Getter
public class PortfolioResponseDto {
    private final Long id;
    private final String userName;
    private final Long totalValue;
    private final List<PortfolioItemResponseDto> portfolioItemResponseDtos;

    public PortfolioResponseDto(Portfolio portfolio, List<PortfolioItemResponseDto> portfolioItemResponseDtos) {
        this.id = portfolio.getId();
        this.userName = portfolio.getVip().getUser().getName();
        this.totalValue = portfolio.getTotalValue();
        this.portfolioItemResponseDtos = portfolioItemResponseDtos;
    }

    public static PortfolioResponseDto from(Portfolio portfolio, List<PortfolioItemResponseDto> portfolioItemResponseDtos) {
        return new PortfolioResponseDto(portfolio, portfolioItemResponseDtos);
    }
}
