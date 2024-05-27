package com.hana.controller;

import com.hana.app.service.fund.FundSecurityService;
import com.hana.app.service.fund.FundService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
        Long relatedFundId = fundService.get(id).get().getId();
        return fundSecurityService.get(relatedFundId);
    }

}
