package com.hana.app.service;

import com.hana.app.data.entity.portfolio.Portfolio;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.repository.portfolio.PortfolioItemRepository;
import com.hana.app.repository.portfolio.PortfolioRepository;
import com.hana.dto.response.PortfolioDto;
import com.hana.dto.response.PortfolioItemDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class PortfolioService {

    final PortfolioRepository portfolioRepository;
    final PortfolioItemRepository portfolioItemRepository;

    public PortfolioDto getPortfolioByUserId(Long userId) {
        PortfolioDto portfolioDto = null;

        try {
            Portfolio portfolio = portfolioRepository.findPortfolioByVipId(userId);
            List<PortfolioItemDto> portfolioItems = getPortfolioItems(portfolioItemRepository.findAllByPortfolioId(portfolio.getId()));
            portfolioDto = PortfolioDto.from(portfolio, portfolioItems);
        } catch (MeteorException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        return portfolioDto;
    }

    private List<PortfolioItemDto> getPortfolioItems(List<PortfolioItem> portfolioItems) {
        List<PortfolioItemDto> list = null;

        for(PortfolioItem portfolioItem : portfolioItems) {
            list.add(PortfolioItemDto.from(portfolioItem));
        }

        return list;
    }
}
