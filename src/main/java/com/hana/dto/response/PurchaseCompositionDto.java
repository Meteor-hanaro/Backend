package com.hana.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PurchaseCompositionDto {
    private final List<CodeQuantityDto> codeQuantityDtos;
    private final long leftover;

    public static PurchaseCompositionDto from(List<CodeQuantityDto> codeQuantityDtoList, Long leftover) {
        return new PurchaseCompositionDto(codeQuantityDtoList, leftover);
    }

}
