package com.hana.fund;

import com.hana.app.data.entity.fund.Fund;
import com.hana.app.data.entity.fund.FundSecurity;
import com.hana.app.service.fund.FundSecurityService;
import com.hana.app.service.fund.FundService;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
@Slf4j
public class SelectFundSecuritiesTests {

    @Autowired
    FundSecurityService fundSecurityService;

    @Test
    void contextLoads() {

        List<FundSecurity> x = fundSecurityService.getAll();
        for (FundSecurity fundSecurity : x) {
            log.info(fundSecurity.getFund().getName());
            log.info(fundSecurity.getSecurity().getName());
            log.info(fundSecurity.getFundSecurityPercentage().toString());
        }
    }
}
