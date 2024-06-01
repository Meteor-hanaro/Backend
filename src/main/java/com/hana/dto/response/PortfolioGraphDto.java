package com.hana.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.List;

@Getter
@RequiredArgsConstructor
public class PortfolioGraphDto {
    private final String securityCode;
    private final List<Long> serialValue;

    public static PortfolioGraphDto from(String securityCode, List<Long> serialValue) {
        return new PortfolioGraphDto(securityCode, serialValue);
    }
}
