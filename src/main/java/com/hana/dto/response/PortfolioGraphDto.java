package com.hana.dto.response;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class PortfolioGraphDto {
    private final String securityCode;
    private final List<LocalDateTime> serialTime;
    private final List<Long> serialValue;

    public static PortfolioGraphDto from(String securityCode, List<LocalDateTime> serialTime, List<Long> serialValue) {
        return new PortfolioGraphDto(securityCode, serialTime, serialValue);
    }
}
