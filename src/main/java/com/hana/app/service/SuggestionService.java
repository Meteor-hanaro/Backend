package com.hana.app.service;

import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import com.hana.app.repository.portfolio.PortfolioRepository;
import com.hana.app.repository.suggestion.SuggestionItemRepository;
import com.hana.app.repository.suggestion.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class SuggestionService {

    final SuggestionRepository suggestionRepository;
    final SuggestionItemRepository suggestionItemRepository;
    final PortfolioRepository portfolioRepository;

    public JSONObject getSuggestionListByUserId(Long userId) {

        // suggestion_name_list -> userid - 포트폴리오조회 - arraylist 에 저장
        Long userPortfolioId = 0L;

        try {
            userPortfolioId = portfolioRepository.findByUserId(userId).getId();
        } catch (Exception e) {
            log.error("Doesn't match user {}portfolio", userId);
            throw new RuntimeException();
        }

        // suggestion_list
        List<Map<Object, Object>> suggestionNameList = new ArrayList<>();
        List<Suggestion> suggestionList = new ArrayList<>();
        Map<Object, Object> map = new HashMap<>();
        JSONObject jsonObject = new JSONObject();

        try {
            suggestionList = suggestionRepository.findAllByPortfolioId(userPortfolioId);

            for (Suggestion suggestion : suggestionList) {
                Map<Object, Object> nameMap = new HashMap<>();
                nameMap.put("suggestion_id", suggestion.getId());
                nameMap.put("suggestion_name", suggestion.getName());
                suggestionNameList.add(nameMap);
            }
        } catch (Exception e) {
            log.error("Doesn't match portfolio {} suggestion", userPortfolioId);
            throw new RuntimeException();
        }

        List<List<SuggestionItem>> suggestionItemList = new ArrayList<>();

        try {
            for (Suggestion suggestion : suggestionList) {
                suggestionItemList.add(suggestionItemRepository.findAllBySuggestion(suggestion));
            }

            jsonObject.put("suggestion_name_list", suggestionNameList);
            jsonObject.put("suggestion_list", suggestionItemList);

        } catch (Exception e) {
            log.error("Error during matching suggestion - suggestionItem");
            throw new RuntimeException();
        }

        return jsonObject;
    }
}
