package com.hana.portfolio;

import com.hana.app.data.entity.fund.FundSecurity;
import com.hana.app.data.entity.security.SecurityPrice;
import com.hana.app.service.PortfolioItemService;
import com.hana.app.service.PortfolioService;
import com.hana.app.service.SecurityPriceService;
import com.hana.app.service.fund.FundSecurityService;
import com.hana.controller.PortfolioController;
import com.hana.dto.response.PortfolioItemDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
@Slf4j
@RequiredArgsConstructor
public class SelectFundGraphDataTests {
    final PortfolioService portfolioService;
    final PortfolioItemService portfolioItemService;
    final SecurityPriceService securityPriceService;
    private final FundSecurityService fundSecurityService;
    PortfolioController portfolioController;
    @Test
    void contextLoads() {
        log.info(portfolioController.getFundGraphData(1L).toString());
    }

}
