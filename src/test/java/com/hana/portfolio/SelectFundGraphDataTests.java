package com.hana.portfolio;

import com.hana.app.service.PortfolioItemService;
import com.hana.app.service.PortfolioService;
import com.hana.app.service.SecurityPriceService;
import com.hana.app.service.fund.FundSecurityService;
import com.hana.controller.PortfolioController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

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
