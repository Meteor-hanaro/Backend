package com.hana.dto.response.suggestion;

import com.hana.app.data.entity.suggestion.Suggestion;
import lombok.Getter;

import java.util.List;

@Getter
public class SuggestionItemDto {
    private final Long suggestionId;
    private final String suggestionName;
    private final List<SuggestionItemCompositionDto> suggestionItems;

    public SuggestionItemDto(Suggestion suggestion, List<SuggestionItemCompositionDto> suggestionItems) {
        this.suggestionId = suggestion.getId();
        this.suggestionName = suggestion.getName();
        this.suggestionItems = suggestionItems;
    }

    public static SuggestionItemDto from(Suggestion suggestion, List<SuggestionItemCompositionDto> suggestionItems) {
        return new SuggestionItemDto(suggestion, suggestionItems);
    }
}
