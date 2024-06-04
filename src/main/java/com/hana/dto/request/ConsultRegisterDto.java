package com.hana.dto.request;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ConsultRegisterDto {
    private Long vipId;
    private Long pbId;
    private String content;
    private LocalDateTime startTime;
}
