package com.hana.controller;

import com.hana.app.service.SuggestionService;
import lombok.RequiredArgsConstructor;
import org.json.simple.JSONObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/suggestion")
@RequiredArgsConstructor
public class SuggestionController {

    final SuggestionService suggestionService;

    // pb가 제시한 수정안을 고객에게 보여주기 위해 데이터 추출
    // /api/suggestion/extract/{고객ID}
    @RequestMapping("/extract")
    public ResponseEntity<Object> extractSuggestions(@RequestParam("userId") Long userId) {
        JSONObject jsonObject = null;

        try {
            // 제시안 ID로 포트폴리오 정보 만들어서 가져온 다음 front 로 전송
            jsonObject = suggestionService.getSuggestionListByUserId(userId);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return new ResponseEntity<>(jsonObject, HttpStatus.OK);
    }
}
