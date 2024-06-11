package com.hana.dto.response.consult;

import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.Consult;
import com.hana.app.data.entity.RiskType;
import lombok.Getter;

@Getter
public class ConsultAdminItemDto {
    private final Long id; // consult id
    private final String vipName;
    private final RiskType riskType;
    private final String pbName;
    private final BaseEntity.BaseState state;

    public ConsultAdminItemDto(Consult consult) {
        this.id = consult.getId();
        this.vipName = consult.getVip().getUser().getName();
        this.riskType = consult.getVip().getRiskType();
        this.pbName = consult.getPb().getUser().getName();
        this.state = consult.getStatus();
    }

    public static ConsultAdminItemDto from(Consult consult) {
        return new ConsultAdminItemDto(consult);
    }
}
