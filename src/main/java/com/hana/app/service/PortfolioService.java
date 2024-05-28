package com.hana.app.service;

import com.hana.app.data.entity.portfolio.Portfolio;
import com.hana.app.repository.portfolio.PortfolioItemRepository;
import com.hana.app.repository.portfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class PortfolioService {

    final PortfolioRepository portfolioRepository;
    final PortfolioItemRepository portfolioItemRepository;

    public JSONObject getPortfolioByUserId(Long userId) {
        JSONObject jsonObject = new JSONObject();
        Portfolio portfolio = null;

        try {
            portfolio = portfolioRepository.findByUserId(userId);
            jsonObject.put("portfolio", portfolio);
            jsonObject.put("portfolio_item", portfolioItemRepository.findAllByPortfolioId(portfolio.getId()));
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RuntimeException();
        }

        return jsonObject;
    }
}
