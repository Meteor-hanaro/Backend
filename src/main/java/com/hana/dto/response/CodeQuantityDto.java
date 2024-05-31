package com.hana.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class CodeQuantityDto {
    private final String securityCode;
    private final Long quantity;

    public static CodeQuantityDto from(String securityCode, Long quantity) {
        return new CodeQuantityDto(securityCode, quantity);
    }
}
