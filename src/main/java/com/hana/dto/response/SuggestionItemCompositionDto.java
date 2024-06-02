package com.hana.dto.response;

import com.hana.app.data.entity.suggestion.SuggestionItem;
import lombok.Getter;

@Getter
public class SuggestionItemCompositionDto {
    private Long suggestionItemId;
    private String suggestionItemName;
    private Long suggestionValue;

    public SuggestionItemCompositionDto(SuggestionItem suggestionItem) {
        this.suggestionItemId = suggestionItem.getFund().getId();
        this.suggestionItemName = suggestionItem.getFund().getName();
        this.suggestionValue = suggestionItem.getFundValue();
    }

    public static SuggestionItemCompositionDto from(SuggestionItem suggestionItem) {
        return new SuggestionItemCompositionDto(suggestionItem);
    }
}
