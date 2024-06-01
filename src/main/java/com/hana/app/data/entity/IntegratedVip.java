package com.hana.app.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

@Entity
@Immutable
@Getter
@Setter
public class IntegratedVip {
    @Id
    Long userId;
    String email;
    String name;
    String phone;
    Long vipId;
    String riskType;
    Long pbId;

    @Override
    public String toString() {
        return "IntegratedVip{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", vipId=" + vipId +
                ", riskType='" + riskType + '\'' +
                ", pbId=" + pbId +
                '}';
    }
}