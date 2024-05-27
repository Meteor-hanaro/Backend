package com.hana.app.data.entity.fund;

import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.RiskType;
import jakarta.persistence.*;
import lombok.*;

@Table(name="fund")
@Getter
@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Fund extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="fund_id", nullable = false)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(name = "risk_type")
    private RiskType riskType;

    @Column(name="fund_name", nullable = false)
    private String name;
}
