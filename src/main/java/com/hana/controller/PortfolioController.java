package com.hana.controller;

import com.hana.app.data.entity.fund.FundSecurity;
import com.hana.app.data.entity.security.SecurityPrice;
import com.hana.app.service.portfolio.PortfolioItemService;
import com.hana.app.service.portfolio.PortfolioService;
import com.hana.app.service.security.SecurityPriceService;
import com.hana.app.service.fund.FundSecurityService;
import com.hana.app.service.fund.FundService;
import com.hana.dto.response.portfolio.PortfolioDto;
import com.hana.dto.response.portfolio.PortfolioGraphDto;
import com.hana.dto.response.portfolio.PortfolioItemDto;
import com.hana.dto.response.portfolio.PortfolioItemResponseDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
@Slf4j
public class PortfolioController {

    final PortfolioService portfolioService;
    final PortfolioItemService portfolioItemService;
    final SecurityPriceService securityPriceService;
    private final FundSecurityService fundSecurityService;
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


    @RequestMapping("/graphData")
    public Object getFundGraphData(@RequestParam("vipId") Long vipId) {

        // 1. vipID 기준으로 그 사람의 포폴을 구성하는 모든 item들 불러오기
        List<PortfolioItemDto> portfolioItems = portfolioService.getPortfolioByVipId(vipId).getPortfolioItems();

        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);
        LocalDateTime earliestDate = portfolioService.getEarliestCreatedAt(portfolioItems);
        HashMap<String, PortfolioGraphDto> return_map = new HashMap<>();

        // 2. 그 item들을 구성하는 security들 관련 데이터 취합
        portfolioItems.forEach((portfolioItemDto -> {
            Long item_fund_id = portfolioItemDto.getFundId();
            String fund_name = portfolioItemDto.getFundName();
            LocalDateTime start_date = portfolioItemDto.getCreatedAt();

            // item_fund_id에 연결된 security들
            List<FundSecurity> fundSecurityList = fundSecurityService.getByFundId(item_fund_id);
            List<PortfolioGraphDto> security_list = new ArrayList<>();
            fundSecurityList.forEach((fundSecurity -> {
                String security_id = fundSecurity.getSecurity().getId();
                List<SecurityPrice> price_history = securityPriceService.getPriceHistory(security_id, start_date, today);
                PortfolioGraphDto filled_history = portfolioService.fillPriceHistory(security_id, price_history, earliestDate, start_date);

                security_list.add(filled_history);
            }));
            return_map.put(portfolioItemDto.getFundName(), portfolioService.assemblePriceHistory(fund_name, security_list));
        }));
        return return_map;
    }
}
