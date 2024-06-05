package com.hana.dto.response;

import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.Consult;
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
