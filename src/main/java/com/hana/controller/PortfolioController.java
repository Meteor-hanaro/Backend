package com.hana.controller;

import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.service.PortfolioItemService;
import com.hana.app.service.PortfolioService;
import com.hana.app.service.fund.FundService;
import com.hana.dto.response.PortfolioDto;
import com.hana.dto.response.PortfolioItemDto;
import com.hana.dto.response.PortfolioItemResponseDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    final PortfolioService portfolioService;
    final PortfolioItemService portfolioItemService;
    private final FundService fundService;

    @RequestMapping("/extract")
    public PortfolioDto getPortfolio(@RequestParam("vipId") Long vipId) {
        PortfolioDto portfolioDto = null;

        try {
            portfolioDto = portfolioService.getPortfolioByVipId(vipId);
        } catch (MeteorException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        return portfolioDto;
    }

    @RequestMapping("/itemValue")
    public List<PortfolioItemResponseDto> getPortfolioItemValue(@RequestParam("vipId") Long vipId) {
        List<PortfolioItemDto> portfolioItems = portfolioService.getPortfolioByVipId(vipId).getPortfolioItems();

        List<PortfolioItemResponseDto> portfolioItemResponseDtos = new ArrayList<>();
        
        portfolioItems.forEach((x) -> {

            String fundName = x.getFundName();
            Long startAmount = x.getStartAmount();
            LocalDateTime startDate = x.getCreatedAt().withHour(0).withMinute(0).withSecond(0).withNano(0);
            Long evaluationAmount = portfolioItemService.getCurrentValue(x.getItemId());
            portfolioItemResponseDtos.add(PortfolioItemResponseDto.from(fundName, startAmount, startDate, evaluationAmount));
        });


        return portfolioItemResponseDtos;
    }
}
