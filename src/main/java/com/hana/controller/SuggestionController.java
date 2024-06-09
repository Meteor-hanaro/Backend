package com.hana.controller;

import com.hana.app.data.entity.VIP;
import com.hana.app.data.entity.portfolio.Portfolio;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import com.hana.app.service.portfolio.PortfolioItemService;
import com.hana.app.service.portfolio.PortfolioService;
import com.hana.app.service.SuggestionService;
import com.hana.dto.request.SuggestionApplyRequestDto;
import com.hana.dto.request.SuggestionApplyRequestItemDto;
import com.hana.dto.response.portfolio.PortfolioItemDto;
import com.hana.dto.response.suggestion.SuggestionDto;
import com.hana.dto.response.suggestion.SuggestionItemObtainDto;
import com.hana.dto.response.suggestion.SuggestionObtainDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/suggestion")
@RequiredArgsConstructor
@Slf4j
public class SuggestionController {

    final SuggestionService suggestionService;
    final PortfolioService portfolioService;
    private final PortfolioItemService portfolioItemService;

    // pb가 제시한 수정안을 고객에게 보여주기 위해 데이터 추출
    // /api/suggestion/extract/{고객ID}
    @RequestMapping("/extract")
    public SuggestionDto extractSuggestions(@RequestParam("userId") Long userId) {
        SuggestionDto suggestionDto = null;
        try {
            // 제시안 ID로 포트폴리오 정보 만들어서 가져온 다음 front 로 전송
            suggestionDto = suggestionService.getSuggestionListByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return suggestionDto;
    }

    @RequestMapping("/append")
    public Long addSuggestion(@RequestParam("vipId") Long vipId) {
        log.info("UserId: {}", vipId);
        Portfolio portfolio = portfolioService.getPortfolioEntityByVipId(vipId);
        List<PortfolioItem> portfolioItems = portfolioItemService.getPortfolioItemEntityByPortfolioId(portfolio.getId());
        Suggestion suggestion = suggestionService.addSuggestion(portfolio);

        portfolioItems.forEach((portfolioItem) -> {
            suggestionService.addSuggestionItem(suggestion, portfolioItem);
        });

        return suggestion.getId();

    }

    @RequestMapping("/obtain")
    public SuggestionObtainDto obtainSuggestion(@RequestParam("suggestion_id") Long suggestionId) {
        log.info("suggestionId: {}", suggestionId);

        SuggestionObtainDto suggestionObtainDto = null;
        List<SuggestionItemObtainDto> suggestionItemObtainDtos = new ArrayList<>();
        VIP vip = suggestionService.getVipBySuggestionId(suggestionId);

        Long vipId = vip.getId();
        String vipName = vip.getUser().getName();
        List<PortfolioItemDto> portfolioItems = portfolioService.getPortfolioByVipId(vipId).getPortfolioItems();

        List<SuggestionItem> suggestionItems = suggestionService.getSuggestionItems(suggestionId);
        portfolioItems.forEach((portfolioItemDto -> {
            SuggestionItem matchingSuggestionItem = suggestionItems.stream().filter(suggestionItem -> suggestionItem.getFund().getId().equals(portfolioItemDto.getFundId())).findFirst().orElse(null);

            assert matchingSuggestionItem != null;
            Long suggestionItemId = matchingSuggestionItem.getId();

            String fundName = portfolioItemDto.getFundName();
            LocalDateTime fundInitDate = portfolioItemDto.getCreatedAt().truncatedTo(ChronoUnit.DAYS);
            Long fundInitValue = portfolioItemDto.getStartAmount();
            Long fundCurrentValue = portfolioItemService.getCurrentValue(portfolioItemDto.getItemId());
            log.info("suggestionItemId: {}, fundName: {}, fundInitDate: {}, fundInitValue: {}, fundCurrentvalue: {}", suggestionItemId, fundName, fundInitDate, fundInitValue, fundCurrentValue);
            SuggestionItemObtainDto suggestionItemObtainDto = new SuggestionItemObtainDto(suggestionItemId, fundName, fundInitDate, fundInitValue, fundCurrentValue);
            suggestionItemObtainDtos.add(suggestionItemObtainDto);
        }));
        log.info(vipName);
        return new SuggestionObtainDto(vipName, suggestionItemObtainDtos);
    }

    @RequestMapping("/apply")
    public void applySuggestion(@RequestBody SuggestionApplyRequestDto suggestionApplyRequestDto) {
        Long suggestionId = suggestionApplyRequestDto.getSuggestionId();
        String suggestionName = suggestionApplyRequestDto.getSuggestionName();
        suggestionService.updateSuggestionNameById(suggestionId, suggestionName);

        List<SuggestionApplyRequestItemDto> suggestionApplyRequestItemDtoList = suggestionApplyRequestDto.getSuggestionApplyRequestItemDtoList();
        suggestionApplyRequestItemDtoList.forEach(suggestionApplyRequestItemDto -> {
            SuggestionItem suggestionItem = suggestionService.getSuggestionItemById(suggestionApplyRequestItemDto.getSuggestionItemId());
            suggestionService.updateSuggestionItemById(suggestionItem.getId(), suggestionApplyRequestItemDto.getNewValue());
        });
    }

    @RequestMapping("/remove")
    public void removeSuggestion(@RequestParam("suggestion_id") Long suggestionId) {
        log.info("Remove suggestionId: {}", suggestionId);
        suggestionService.removeSuggestionBySuggestionId(suggestionId);
    }
}

