package com.hana.fund;

import com.hana.app.data.entity.fund.Fund;
import com.hana.app.service.fund.FundService;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@Slf4j
@SpringBootTest
public class SelectTests {

    @Autowired
    FundService fundService;

    @Test
    void contextLoads() {
        List<Fund> x = fundService.getAll();
        for (Fund fund : x) {
            log.info(fund.getId().toString());
            log.info(fund.getName());
            log.info(fund.getRiskType().toString());
        }
    }

}
