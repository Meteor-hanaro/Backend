package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="portfolio_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class PortfolioItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    @Column(name = "portfolio_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="portfolio_id")
    private Portfolio portfolio;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id")
    private Fund fund;

    @Column(nullable = false, name = "start_amount")
    private Long startAmount;

    @Column(nullable = false, name = "evaluation_amount")
    private Long evaluationAmount;
}
