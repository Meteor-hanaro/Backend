package com.hana.app.data.entity;

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
    @OneToOne
    @JoinColumn(name="risktype_id")
    private Risktype risktype;
    @Column(name="fund_name", nullable = false)
    private String name;
}
