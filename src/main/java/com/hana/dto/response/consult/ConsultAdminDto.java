package com.hana.dto.response.consult;

import lombok.Getter;

import java.util.List;

@Getter
public class ConsultAdminDto {
    public List<ConsultAdminItemDto> consultAdminItemDtos;

    public ConsultAdminDto(List<ConsultAdminItemDto> consultAdminItemDtos) {
        this.consultAdminItemDtos = consultAdminItemDtos;
    }

    public static ConsultAdminDto from(List<ConsultAdminItemDto> consultAdminItemDtos) {
        return new ConsultAdminDto(consultAdminItemDtos);
    }
}
