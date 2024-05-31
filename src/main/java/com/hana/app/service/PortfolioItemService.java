package com.hana.app.service;

import com.hana.app.data.entity.fund.Fund;
import com.hana.app.data.entity.fund.FundSecurity;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.repository.fund.FundRepository;
import com.hana.app.repository.fund.FundSecurityRepository;
import com.hana.app.repository.security.SecurityPriceRepository;
import com.hana.dto.response.CodeQuantityDto;
import com.hana.dto.response.PurchaseCompositionDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class PortfolioItemService {

    final FundSecurityRepository fundSecurityRepository;
    final SecurityPriceRepository securityPriceRepository;
    final FundRepository fundRepository;

    public String dummy() {
        return "HEHEH";
    }
    //    가입 당시 구조 확보
    public PurchaseCompositionDto getSecurityQuantity(PortfolioItem portfolioItem, Long inputValue) {
        Optional<Fund> fund_connected_with_item = fundRepository.findById(portfolioItem.getFund().getId());
        LocalDateTime purchase_date = portfolioItem.getCreatedAt();
        List<CodeQuantityDto> codeQuantityDtos = new ArrayList<>();
        if (fund_connected_with_item.isPresent()) {
            Fund fund = fund_connected_with_item.get();
//            펀드의 구조
            List<FundSecurity> fundSecurities = fundSecurityRepository.findFundSecuritiesByFund_IdOrderByFundSecurityPercentageDesc(fund.getId());
            fundSecurities.forEach(security -> {
//                펀드 구입 시점의 security의 가격
                Long init_price = securityPriceRepository.findSecurityPriceByTradeDateAndSecurityId(purchase_date, security.getSecurity().getId()).getTradePrice();
                Long init_quantity = inputValue * (security.getFundSecurityPercentage() / 100) / init_price;
                codeQuantityDtos.add(CodeQuantityDto.from(security.getSecurity().getId(), init_quantity));
            });

            return PurchaseCompositionDto.from(codeQuantityDtos);
        }
        return null;
    }
}
