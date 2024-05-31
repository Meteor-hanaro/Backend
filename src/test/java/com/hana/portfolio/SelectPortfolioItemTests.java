package com.hana.portfolio;

import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.service.PortfolioItemService;
import com.hana.dto.response.PurchaseCompositionDto;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

@SpringBootTest
@Slf4j
public class SelectPortfolioItemTests {

    @Autowired
    PortfolioItemService portfolioItemService;

    @Test
    void contextLoads() {
        Optional<PortfolioItem> pi = portfolioItemService.getPortfolioItem(299L);
        if (pi.isPresent()) {
            PortfolioItem portfolioItem = pi.get();
            PurchaseCompositionDto securityQuantity = portfolioItemService.getSecurityQuantity(portfolioItem, 200000000L);
            if (securityQuantity != null) {
                securityQuantity.getCodeQuantityDtos().forEach((x) -> {
                });
            } else {
                log.info("Security quantity is null");
            }
        } else {
            log.info("Portfolio Item Not Found");
        }
    }

    @Test
    void contextLoads2() {
        log.info("START");
        log.info(portfolioItemService.getCurrentValue(299L).toString());
        log.info("END");
    }
}
