package com.hana.controller;

import com.hana.app.data.entity.VIP;
import com.hana.app.data.entity.portfolio.Portfolio;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import com.hana.app.service.PortfolioItemService;
import com.hana.app.service.PortfolioService;
import com.hana.app.service.SuggestionService;
import com.hana.dto.request.AddFundToSuggestionRequestDto;
import com.hana.dto.request.SuggestionApplyRequestDto;
import com.hana.dto.request.SuggestionApplyRequestItemDto;
import com.hana.dto.response.SuggestionDto;
import com.hana.dto.response.SuggestionItemObtainDto;
import com.hana.dto.response.SuggestionObtainDto;
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
import java.util.Random;

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

    public static LocalDateTime getRandomDate(LocalDateTime startDate, LocalDateTime endDate) {
        long daysBetween = ChronoUnit.DAYS.between(startDate, endDate);
        Random random = new Random();
        long randomDays = random.nextLong(daysBetween + 1); // +1 to include end date
        return startDate.plusDays(randomDays);
    }

    @RequestMapping("/obtain")
    public SuggestionObtainDto obtainSuggestion(@RequestParam("suggestion_id") Long suggestionId) {
        log.info("suggestionId: {}", suggestionId);

        SuggestionObtainDto suggestionObtainDto = null;
        List<SuggestionItemObtainDto> suggestionItemObtainDtos = new ArrayList<>();
        VIP vip = suggestionService.getVipBySuggestionId(suggestionId);

        Long vipId = vip.getId();
        String vipName = vip.getUser().getName();


        List<SuggestionItem> suggestionItems = suggestionService.getSuggestionItems(suggestionId);

        suggestionItems.forEach((suggestionItem) -> {
            Long suggestionItemId = suggestionItem.getId();
            String fundName = suggestionItem.getFund().getName();
            LocalDateTime startDate = LocalDateTime.of(2020, 5, 16, 0, 0);
            LocalDateTime endDate = LocalDateTime.of(2024, 5, 1, 0, 0);

            LocalDateTime fundInitDate = getRandomDate(startDate, endDate);
            Long fundInitValue = suggestionItem.getFundValue();
            Long fundCurrentValue = suggestionService.calcCurSuggestionValue(suggestionItemId, fundInitValue, fundInitDate);
            SuggestionItemObtainDto suggestionItemObtainDto = new SuggestionItemObtainDto(suggestionItemId, fundName, fundInitDate, fundInitValue, fundCurrentValue);
            suggestionItemObtainDtos.add(suggestionItemObtainDto);
        });

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

    @RequestMapping("/fund/add")
    public void addFundToSuggestion(@RequestBody AddFundToSuggestionRequestDto addFundToSuggestionRequestDto) {
        suggestionService.addFundToSuggestion(addFundToSuggestionRequestDto);
    }

    @RequestMapping("/fund/remove")
    public void removeFundFromSuggestion(@RequestParam("suggestion_item_id") Long suggestion_item_id) {
        suggestionService.removeSuggestionItem(suggestion_item_id);
    }
}

