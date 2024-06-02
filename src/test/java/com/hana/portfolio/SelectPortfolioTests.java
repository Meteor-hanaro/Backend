package com.hana.portfolio;

import com.hana.app.service.PortfolioService;
import com.hana.dto.response.PortfolioDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@Slf4j
public class SelectPortfolioTests {

    @Autowired
    PortfolioService portfolioService;

    @Test
    void contextLoads() {
        PortfolioDto x = portfolioService.getPortfolioByVipId(1L);
        log.info("{}, {}", x.getUserName(), x.getId());
    }
}
