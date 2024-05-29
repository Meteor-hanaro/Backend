package com.hana.app.data.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor

public enum RiskType {
    STABLE("안정형"),
    CONSERVATIVE("안정추구형"),
    NEUTRAL("위험중립형"),
    GROWTH("성장추구형"),
    AGGRESSIVE("성장형");

    private final String description;
}
