package com.hana.dto.request;

import lombok.Getter;

@Getter
public class AddFundToSuggestionRequestDto {
    Long suggestionId;
    Long fundId;
    Long fundValue;

    public AddFundToSuggestionRequestDto(Long suggestionId, Long fundId, Long fundValue) {
        this.suggestionId = suggestionId;
        this.fundId = fundId;
        this.fundValue = fundValue;
    }
}
