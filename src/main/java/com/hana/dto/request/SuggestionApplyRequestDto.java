package com.hana.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SuggestionApplyRequestDto {
    Long suggestionId;
    String suggestionName;
    List<SuggestionApplyRequestItemDto> suggestionApplyRequestItemDtoList;

    public SuggestionApplyRequestDto(Long suggestionId, String suggestionName, List<SuggestionApplyRequestItemDto> suggestionApplyRequestItemDtoList) {
        this.suggestionId = suggestionId;
        this.suggestionName = suggestionName;
        this.suggestionApplyRequestItemDtoList = suggestionApplyRequestItemDtoList;
    }
}
