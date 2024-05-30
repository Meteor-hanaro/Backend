package com.hana.app.service;

import com.hana.app.data.entity.fund.Fund;
import com.hana.app.data.entity.fund.FundSecurity;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.repository.fund.FundSecurityRepository;
import com.hana.app.repository.security.SecurityPriceRepository;
import com.hana.dto.response.CodeQuantityDto;
import com.hana.dto.response.PurchaseCompositionDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PortfolioItemService {

    final FundSecurityRepository fundSecurityRepository;
    final SecurityPriceRepository securityPriceRepository;

//    가입 당시 구조 확보
    public PurchaseCompositionDto getSecurityQuantity(PortfolioItem portfolioItem, Long inputValue) {
        List<FundSecurity> fundSecurities = fundSecurityRepository.findFundSecuritiesByFund_IdOrderByFundSecurityPercentageDesc(portfolioItem.getFund().getId());
        Long leftover = 0L;
        List<CodeQuantityDto> codeQuantityDtoList = new ArrayList<>();
        for (FundSecurity fs : fundSecurities) {
            long inputMoney = inputValue * fs.getFundSecurityPercentage() / 100;
            long value_back_then = securityPriceRepository.findSecurityPriceByTradeDateAndSecurityId(portfolioItem.getCreatedAt(), fs.getSecurity().getId()).getTradePrice();
            int purchased_quantity = (int) Math.floor((double) inputMoney / value_back_then);
            leftover += inputMoney - purchased_quantity * value_back_then;
            codeQuantityDtoList.add(CodeQuantityDto.from(fs.getSecurity().getId(), purchased_quantity));
        }

        return PurchaseCompositionDto.from(codeQuantityDtoList, leftover);
    }
}
