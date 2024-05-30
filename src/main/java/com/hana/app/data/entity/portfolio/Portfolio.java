package com.hana.app.data.entity.portfolio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.User;
import com.hana.app.data.entity.VIP;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="portfolio")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Portfolio extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "portfolio_id")
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "vip_id")
    private VIP vip;

    @Column(nullable = false, name = "total_value")
    private long totalValue;

}
