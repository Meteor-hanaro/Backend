package com.hana.dto.response.suggestion;

import com.hana.app.data.entity.suggestion.Suggestion;
import lombok.Getter;

import java.util.List;

@Getter
public class SuggestionDto {
    private final Long id;
    private final List<SuggestionItemDto> suggestionItems;

    public SuggestionDto(Suggestion suggestion, List<SuggestionItemDto> suggestionItems) {
        this.id = suggestion.getId();
        this.suggestionItems = suggestionItems;
    }

    public static SuggestionDto from(Suggestion suggestion, List<SuggestionItemDto> suggestionItems) {
        return new SuggestionDto(suggestion, suggestionItems);
    }
}
