package com.hana.dto.response.security;

import com.hana.app.data.entity.security.Security;
import lombok.Getter;

@Getter
public class SecurityItemDto {
    private final String id;
    private final String name;
    private final boolean isFund;

    public SecurityItemDto(Security security) {
        this.id = security.getId();
        this.name = security.getName();
        this.isFund = !security.getId().startsWith("K");
    }

    public static SecurityItemDto from(Security security){
        return new SecurityItemDto(security);
    }
}
