package com.hana.dto.response.suggestion;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class SuggestionItemObtainDto {
    Long suggestionItemId;
    String fundName;
    LocalDateTime fundInitDate;
    Long fundInitValue;
    Long fundCurrentValue;

    public SuggestionItemObtainDto(Long suggestionItemId, String fundName, LocalDateTime fundInitDate, Long fundInitValue, Long fundCurrentValue) {
        this.suggestionItemId = suggestionItemId;
        this.fundName = fundName;
        this.fundInitDate = fundInitDate;
        this.fundInitValue = fundInitValue;
        this.fundCurrentValue = fundCurrentValue;
    }
}
