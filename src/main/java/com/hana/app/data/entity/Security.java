package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "security")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class Security extends BaseEntity {
    @Id
    @Column(name = "security_id")
    private String id;

    @Column(nullable = false, name="security_name")
    private String name;

    public static Security createSecurity(String code, String name) {
        // 필요에 따라 추가적인 초기화 작업 수행
        return new Security(code, name);
    }
}
