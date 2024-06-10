package com.hana.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SuggestionApplyRequestItemDto {
    Long suggestionItemId;
    Long newValue;

    public SuggestionApplyRequestItemDto(Long suggestionItemId, Long newValue) {
        this.suggestionItemId = suggestionItemId;
        this.newValue = newValue;
    }
}
