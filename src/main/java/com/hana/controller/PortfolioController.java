package com.hana.controller;

import com.hana.app.service.portfolio.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/portfolio")
public class PortfolioController {
    final PortfolioService portfolioService;

    @RequestMapping("/get")
    public Object getPortfoliosByUserId(Long userId) {
        return portfolioService.get(userId);
    }
}
