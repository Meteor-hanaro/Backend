package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "vip")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class VIP extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "vip_id", nullable = false)
    private Long id;

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = false)
    private Users user;

    @ManyToOne
    @JoinColumn(name = "pb_id", nullable = false)
    private Pb pb;

    @Enumerated(EnumType.STRING)
    @Column(name = "risk_type")
    private RiskType riskType;

    @Column(name = "birth_date", nullable = false)
    private LocalDate birthDate;

    @Column(name = "gender", nullable = false)
    private Integer gender; // 0: 남자, 1: 여자

    @Column(name = "tested_at")
    private LocalDateTime testedAt;
}
