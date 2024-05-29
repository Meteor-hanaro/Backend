package com.hana.controller;

import com.hana.app.data.entity.fund.Fund;
import com.hana.app.service.fund.FundSecurityService;
import com.hana.app.service.fund.FundService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@Slf4j
@RestController
@RequestMapping("/api/fund")
@RequiredArgsConstructor
public class FundController {
    final FundService fundService;
    final FundSecurityService fundSecurityService;

    @RequestMapping("/get")
    public Object getFund() {
        return fundService.getAll();
    }

    @RequestMapping("/securities/get")
    public Object getFundSecurities(@RequestParam("id") Long id) {
        Optional<Fund> relatedFund = fundService.get(id);

        return fundSecurityService.getByFundId(relatedFund.get().getId());
    }

}
