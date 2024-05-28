package com.hana.app.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import com.hana.app.repository.suggestion.SuggestionItemRepository;
import com.hana.app.repository.suggestion.SuggestionRepository;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class SuggestionService {

    final SuggestionRepository suggestionRepository;
    final SuggestionItemRepository suggestionItemRepository;

    public JSONObject getSuggestionListBySuggestionId(Long suggestionId) {
        // suggestionId로 조회한 다음 포트폴리오가 어떻게 구성되어 있는지 데이터 전달
        Suggestion suggestion;
        List<SuggestionItem> suggestionItemList;
//        String jsonString = "";
        Map<Object, Object> map = new HashMap<>();
        JSONObject jsonObject = null;

        try {
            suggestion = suggestionRepository.findById(suggestionId).orElse(null);
            suggestionItemList = suggestionItemRepository.findAllBySuggestion(suggestion);

            map.put("suggestion_name", suggestion.getName());
            map.put("suggestion_list", suggestionItemList);
            jsonObject = new JSONObject(map);
            
//            ObjectMapper mapper = new ObjectMapper();
//            mapper.registerModule(new JavaTimeModule());
//            jsonString += mapper.writeValueAsString(suggestion.getName());
//            jsonString += mapper.writeValueAsString(suggestionItemList);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject;
    }
}
