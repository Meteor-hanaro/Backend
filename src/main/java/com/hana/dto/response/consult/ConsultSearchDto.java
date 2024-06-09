package com.hana.dto.response.consult;

import lombok.Getter;

import java.util.List;

@Getter
public class ConsultSearchDto {
    private final List<ConsultWebRTCRoomDto> isExistConsult;

    public ConsultSearchDto(List<ConsultWebRTCRoomDto> list) {
        this.isExistConsult = list;
    }

    public static ConsultSearchDto from(List<ConsultWebRTCRoomDto> list){
        return new ConsultSearchDto(list);
    }
}
