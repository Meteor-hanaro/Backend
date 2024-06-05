package com.hana.app.data.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Immutable;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Immutable
@Getter
@Setter
public class IntegratedVip {
    @Id
    Long userId;
    String email;
    String password;
    Boolean isvip;
    String name;
    String phone;
    Long vipId;
    String riskType;
    LocalDateTime testedAt;
    Long pbId;

    @Override
    public String toString() {
        return "IntegratedVip{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", isvip=" + isvip + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", vipId=" + vipId +
                ", riskType='" + riskType + '\'' +
                ", testedAt=" + testedAt +
                ", pbId=" + pbId +
                '}';
    }
}