package com.hana.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PurchaseCompositionDto {
    private final List<CodeQuantityDto> codeQuantityDtos;

    public static PurchaseCompositionDto from(List<CodeQuantityDto> codeQuantityDtoList) {
        return new PurchaseCompositionDto(codeQuantityDtoList);
    }

}
