package com.hana.dto.response;

import com.hana.app.data.entity.suggestion.Suggestion;
import com.hana.app.data.entity.suggestion.SuggestionItem;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SuggestionDto {
    private final Long id;
    private final String name;
    private final List<SuggestionItem> suggestionItems;

    public SuggestionDto(Suggestion suggestion, List<SuggestionItem> suggestionItems) {
        this.id = suggestion.getId();
        this.name = suggestion.getName();
        this.suggestionItems = suggestionItems;
    }

    public static SuggestionDto from(Suggestion suggestion, List<SuggestionItem> suggestionItems) {
        return new SuggestionDto(suggestion, suggestionItems);
    }
}
