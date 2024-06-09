package com.hana.dto.response;

import lombok.Getter;

import java.util.List;
@Getter
public class SuggestionObtainDto {
    String vipName;
    List<SuggestionItemObtainDto> suggestionItems;

    public SuggestionObtainDto(String vipName, List<SuggestionItemObtainDto> suggestionItems) {
        this.vipName = vipName;
        this.suggestionItems = suggestionItems;
    }
}
