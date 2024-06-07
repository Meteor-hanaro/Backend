package com.hana.dto.response;

import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.Consult;
import lombok.Getter;

@Getter
public class ConsultWebRTCRoomDto {
    private final Long vipId;
    private final Long consultId;
    private final BaseEntity.BaseState state;

    public ConsultWebRTCRoomDto(Consult consult) {
        this.vipId = consult.getVip().getId();
        this.consultId = consult.getId();
        this.state = BaseEntity.BaseState.ACTIVE;
    }

    public static ConsultWebRTCRoomDto from(Consult consult) {
        return new ConsultWebRTCRoomDto(consult);
    }
}
