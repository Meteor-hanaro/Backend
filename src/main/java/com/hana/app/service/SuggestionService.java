package com.hana.app.service;

import com.hana.app.data.entity.VIP;
import com.hana.app.data.entity.fund.Fund;
import com.hana.app.data.entity.fund.FundSecurity;
import com.hana.app.data.entity.portfolio.Portfolio;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.data.entity.security.SecurityPrice;
import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import com.hana.app.repository.fund.FundSecurityRepository;
import com.hana.app.repository.portfolio.PortfolioRepository;
import com.hana.app.repository.security.SecurityPriceRepository;
import com.hana.app.repository.suggestion.SuggestionItemRepository;
import com.hana.app.repository.suggestion.SuggestionRepository;
import com.hana.app.service.fund.FundService;
import com.hana.dto.request.AddFundToSuggestionRequestDto;
import com.hana.dto.response.CodeQuantityDto;
import com.hana.dto.response.SuggestionDto;
import com.hana.dto.response.SuggestionItemCompositionDto;
import com.hana.dto.response.SuggestionItemDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class SuggestionService {

    final SuggestionRepository suggestionRepository;
    final SuggestionItemRepository suggestionItemRepository;
    final PortfolioRepository portfolioRepository;
    private final FundService fundService;
    private final FundSecurityRepository fundSecurityRepository;
    private final SecurityPriceRepository securityPriceRepository;

    public SuggestionDto getSuggestionListByUserId(Long userId) {

        Long userPortfolioId = getPortfolioIdByUserId(userId);
        List<Suggestion> suggestions = getSuggestionsByPortfolioId(userPortfolioId);

        return getSuggestions(suggestions);
    }

    private Long getPortfolioIdByUserId(Long vipId) {
        Long id = 0L;

        try {
            id = portfolioRepository.findPortfolioByVipId(vipId).getId();
        } catch (MeteorException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        return id;
    }

    private List<Suggestion> getSuggestionsByPortfolioId(Long portfolioId) {
        List<Suggestion> suggestions = null;

        try {
            suggestions = suggestionRepository.findAllByPortfolioId(portfolioId);
        } catch (MeteorException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        return suggestions;
    }

    private SuggestionDto getSuggestions(List<Suggestion> suggestions) {
        List<SuggestionItemDto> suggestionItemDtos = new ArrayList<>();

        for (Suggestion suggestion : suggestions) {

            List<SuggestionItem> suggestionItems = null;
            List<SuggestionItemCompositionDto> suggestionItemCompositionDtoList = new ArrayList<>();

            try {
                suggestionItems = suggestionItemRepository.findAllBySuggestion(suggestion);

                for (SuggestionItem si : suggestionItems) {
                    suggestionItemCompositionDtoList.add(SuggestionItemCompositionDto.from(si));
                }

                suggestionItemDtos.add(SuggestionItemDto.from(suggestion, suggestionItemCompositionDtoList));
            } catch (MeteorException e) {
                throw new NotFoundException(ErrorType.NOT_FOUND);
            }

        }

        return new SuggestionDto(suggestions.get(0), suggestionItemDtos);
    }

    public List<SuggestionItem> getSuggestionItems(Long suggestionId) {
        return suggestionItemRepository.findAllBySuggestionId(suggestionId);
    }

    @Transactional
    public void deleteAllSuggestion(Portfolio portfolio) {
        List<Suggestion> suggestions = suggestionRepository.findAllByPortfolioId(portfolio.getId());
        suggestions.stream().forEach(suggestion -> suggestionItemRepository.deleteAllBySuggestionId(suggestion.getId()));

        suggestionRepository.deleteAll(suggestions);
    }

    public Suggestion addSuggestion(Portfolio portfolio) {
        Suggestion suggestion = new Suggestion(portfolio, "");
        return suggestionRepository.save(suggestion);
    }

    public SuggestionItem addSuggestionItem(Suggestion suggestion, PortfolioItem portfolioItem) {
        SuggestionItem suggestionItem = new SuggestionItem(portfolioItem.getStartAmount(), portfolioItem.getFund(), suggestion);
        return suggestionItemRepository.save(suggestionItem);
    }

    public VIP getVipBySuggestionId(Long suggestionId) {
        return suggestionRepository.findById(suggestionId).get().getPortfolio().getVip();
    }

    public SuggestionItem getSuggestionItemById(Long suggestionItemId) {
        return suggestionItemRepository.findById(suggestionItemId).get();
    }

    @Transactional
    public int updateSuggestionItemById(Long suggestionItemId, Long newValue) {
        return suggestionItemRepository.updateFundValueById(suggestionItemId, newValue);
    }

    @Transactional
    public int updateSuggestionNameById(Long suggestionId, String newName) {
        return suggestionRepository.updateSuggestionNameById(suggestionId, newName);
    }

    @Transactional
    public void removeSuggestionBySuggestionId(Long suggestionId) {
        suggestionItemRepository.deleteAllBySuggestionId(suggestionId);
        suggestionRepository.removeSuggestionById(suggestionId);

    }

    public void addFundToSuggestion(AddFundToSuggestionRequestDto addFundToSuggestionRequestDto) {
        Long suggestionId = addFundToSuggestionRequestDto.getSuggestionId();
        Suggestion suggestion = suggestionRepository.findSuggestionById(suggestionId);

        Long fundId = addFundToSuggestionRequestDto.getFundId();
        Fund fund = fundService.get(fundId).get();
        Long fundValue = addFundToSuggestionRequestDto.getFundValue();

        SuggestionItem suggestionItem = new SuggestionItem(fundValue, fund, suggestion);

        suggestionItemRepository.save(suggestionItem);
    }

    public long calcCurSuggestionValue(Long suggestionItemId, Long fundInitValue, LocalDateTime fundInitDate) {
        SuggestionItem suggestionItem = suggestionItemRepository.findSuggestionItemById(suggestionItemId);
        Fund fund_connected_with_suggestion = suggestionItem.getFund();
        List<CodeQuantityDto> codeQuantityDtos = new ArrayList<>();
        List<FundSecurity> fundSecurities = fundSecurityRepository.findFundSecuritiesByFund_IdOrderByFundSecurityPercentageDesc(fund_connected_with_suggestion.getId());

        fundSecurities.forEach(security -> {
            SecurityPrice securityPrice = securityPriceRepository.findSecurityPriceByTradeDateAndSecurityId(fundInitDate, security.getSecurity().getId());
            Long init_price = 10000L;
            if (securityPrice != null) {
                init_price = securityPrice.getTradePrice();
            }

            long init_quantity = fundInitValue * security.getFundSecurityPercentage() / init_price;
            codeQuantityDtos.add(CodeQuantityDto.from(security.getSecurity().getId(), init_quantity / 1000));
        });

        List<Long> prices = new ArrayList<>();

        codeQuantityDtos.forEach((codeQuantityDto -> {
            SecurityPrice temp_price = null;
            int minus_days = 0;
            do {
                LocalDateTime n = LocalDateTime.now().minusDays(minus_days++).withHour(0).withMinute(0).withSecond(0).withNano(0);
                temp_price = securityPriceRepository.findSecurityPriceByTradeDateAndSecurityId(n, codeQuantityDto.getSecurityCode());
            } while (temp_price == null);
            String security_code = codeQuantityDto.getSecurityCode();
            Long quantity = codeQuantityDto.getQuantity();
            Long cur_price = temp_price.getTradePrice();
            prices.add(quantity * cur_price);
        }));
        return prices.stream().mapToLong(i -> i).sum();

    }

    @Transactional
    public void removeSuggestionItem(Long suggestionItemId) {
        suggestionItemRepository.deleteSuggestionItemById(suggestionItemId);
    }
}
