package com.hana.controller;

import com.hana.app.service.PortfolioService;
import com.hana.dto.response.PortfolioDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    final PortfolioService portfolioService;

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
}
