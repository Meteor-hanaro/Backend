package com.hana.app.data.entity.portfolio;

import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.VIP;
import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "portfolio")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Portfolio extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vip_id")
    private VIP vip;

    @Column(nullable = false, name = "total_value")
    private long totalValue;

}
