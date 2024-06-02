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
public class IntegratedPb {
    @Id
    Long userId;
    String email;
    String name;
    String phone;
    Long pbId;
    String image;
    String introduce;

    @Override
    public String toString() {
        return "IntegratedPb{" +
                "userId=" + userId +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", phone='" + phone + '\'' +
                ", pbId=" + pbId +
                ", image='" + image + '\'' +
                ", introduce='" + introduce + '\'' +
                '}';
    }
}