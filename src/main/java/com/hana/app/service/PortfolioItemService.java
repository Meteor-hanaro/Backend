package com.hana.app.service;

import com.hana.app.data.entity.fund.Fund;
import com.hana.app.data.entity.fund.FundSecurity;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.data.entity.security.SecurityPrice;
import com.hana.app.repository.fund.FundRepository;
import com.hana.app.repository.fund.FundSecurityRepository;
import com.hana.app.repository.portfolio.PortfolioItemRepository;
import com.hana.app.repository.security.SecurityPriceRepository;
import com.hana.dto.response.CodeQuantityDto;
import com.hana.dto.response.PortfolioItemResponseDto;
import com.hana.dto.response.PurchaseCompositionDto;
import com.hana.exception.MeteorException;
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
    final PortfolioItemRepository portfolioItemRepository;

    public Optional<PortfolioItem> getPortfolioItem(Long id) {
        return portfolioItemRepository.findById(id);
    }

    //    가입 당시 구조 확보
    public PurchaseCompositionDto getSecurityQuantity(PortfolioItem portfolioItem, Long inputValue) throws MeteorException {
        Optional<Fund> fund_connected_with_item = fundRepository.findById(portfolioItem.getFund().getId());
        LocalDateTime purchase_date = portfolioItem.getCreatedAt();
        List<CodeQuantityDto> codeQuantityDtos = new ArrayList<>();
        if (fund_connected_with_item.isPresent()) {
            Fund fund = fund_connected_with_item.get();
//            펀드의 구조
            List<FundSecurity> fundSecurities = fundSecurityRepository.findFundSecuritiesByFund_IdOrderByFundSecurityPercentageDesc(fund.getId());
            if (!fundSecurities.isEmpty()) {
                fundSecurities.forEach(security -> {
                    SecurityPrice securityPrice = securityPriceRepository.findSecurityPriceByTradeDateAndSecurityId(purchase_date, security.getSecurity().getId());
                    Long init_price = 100L;
                    if (securityPrice != null) {
                        init_price = securityPrice.getTradePrice();
                    }


                    long init_quantity = inputValue * security.getFundSecurityPercentage() / init_price;
                    codeQuantityDtos.add(CodeQuantityDto.from(security.getSecurity().getId(), init_quantity / 100));
                });
            } else {
                return null;
            }

            return PurchaseCompositionDto.from(codeQuantityDtos);
        }
        return null;
    }

    //    포트폴리오를 구성하는 펀드 하나의 현 시점 가치를 계산하기.
    public Long getCurrentValue(Long portfolioItemId) {
        Optional<PortfolioItem> portfolioItem = portfolioItemRepository.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            PurchaseCompositionDto securityQuantity = getSecurityQuantity(portfolioItem.get(), portfolioItem.get().getStartAmount());
            List<Long> prices = new ArrayList<>();
            securityQuantity.getCodeQuantityDtos().forEach((x) -> {
//                공휴일이거나 주말이거나 하면 데이터가 빈다. 어떻게 해야?
                SecurityPrice temp_price = null;
                int minus_days = 0;
                do {
                    LocalDateTime n = LocalDateTime.now().minusDays(minus_days++).withHour(0).withMinute(0).withSecond(0).withNano(0);
                    temp_price = securityPriceRepository.findSecurityPriceByTradeDateAndSecurityId(n, x.getSecurityCode());
                } while (temp_price == null);

                String security_code = x.getSecurityCode();
                Long quantity = x.getQuantity();
                Long cur_price = temp_price.getTradePrice();
                prices.add(quantity * cur_price);

            });

            return prices.stream().mapToLong(i -> i).sum();
        }

        return 0L;
    }

    public PortfolioItemResponseDto getPortfolioItemResponseDto(PortfolioItem portfolioItem) {
        return PortfolioItemResponseDto.from(portfolioItem.getFund().getName(), portfolioItem.getStartAmount(), portfolioItem.getCreatedAt(), getCurrentValue(portfolioItem.getId()));
    }

}
