package com.hana.dto.request;

import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@NoArgsConstructor
public class SuggestionApplyRequestDto {
    List<SuggestionApplyRequestItemDto> suggestionApplyRequestItemDtoList;

    public SuggestionApplyRequestDto(List<SuggestionApplyRequestItemDto> suggestionApplyRequestItemDtoList) {
        this.suggestionApplyRequestItemDtoList = suggestionApplyRequestItemDtoList;
    }
}
