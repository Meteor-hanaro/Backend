package com.hana.dto.response;

import com.hana.app.data.entity.security.Security;
import lombok.Getter;

import java.util.List;

@Getter
public class SecurityDto {
    private final List<SecurityItemDto> list;

    public SecurityDto(List<SecurityItemDto> list) {
        this.list = list;
    }

    public static SecurityDto from(List<SecurityItemDto> list) {
        return new SecurityDto(list);
    }
}
