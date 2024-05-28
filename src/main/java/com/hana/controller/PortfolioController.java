package com.hana.controller;

import com.hana.app.service.PortfolioService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/api/portfolio")
@RequiredArgsConstructor
public class PortfolioController {

    final PortfolioService portfolioService;

    @RequestMapping("/extract")
    public ResponseEntity<Object> getPortfolio(@RequestParam("userId") Long userId) {
        JSONObject jsonObject = null;

        try {
            jsonObject = portfolioService.getPortfolioByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);

    }
}
