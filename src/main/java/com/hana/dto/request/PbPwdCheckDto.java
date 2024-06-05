package com.hana.dto.request;

import lombok.Getter;

@Getter
public class PbPwdCheckDto {
    private Long pbId;
    private String inputPwd;
}
