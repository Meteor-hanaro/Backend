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
//                펀드 구입 시점의 security의 가격
                    SecurityPrice securityPrice = securityPriceRepository.findSecurityPriceByTradeDateAndSecurityId(purchase_date, security.getSecurity().getId());
                    Long init_price = securityPrice.getTradePrice();

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

    public Long getCurrentValue(Long portfolioItemId) {
        Optional<PortfolioItem> portfolioItem = portfolioItemRepository.findById(portfolioItemId);

        if (portfolioItem.isPresent()) {
            PurchaseCompositionDto securityQuantity = getSecurityQuantity(portfolioItem.get(), portfolioItem.get().getStartAmount());
            List<Long> prices = new ArrayList<>();
            securityQuantity.getCodeQuantityDtos().forEach((x) -> {
//                공휴일이거나 주말이거나 하면 데이터가 빈다. 어떻게 해야?
                SecurityPrice temp_price = null;
                int minus_days = 365;
                do {
                    LocalDateTime n = LocalDateTime.now().minusDays(minus_days++).withHour(0).withMinute(0).withSecond(0).withNano(0);
                    temp_price = securityPriceRepository.findSecurityPriceByTradeDateAndSecurityId(n, x.getSecurityCode());
                    log.info("{}, {}", n, x.getSecurityCode());
                } while (temp_price == null);

                String security_code = x.getSecurityCode();
                Long quantity = x.getQuantity();
                Long cur_price = temp_price.getTradePrice();
                log.info("Code: {} Quantity: {} Trade price: {}, cur_val: {}", security_code, quantity, LocalDateTime.now(), quantity * cur_price);
                prices.add(quantity * cur_price);

            });

            return prices.stream().mapToLong(i -> i).sum();
        }

        return 0L;
    }

}
