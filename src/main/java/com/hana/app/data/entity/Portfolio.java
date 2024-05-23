package com.hana.app.data.entity;

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
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false, name = "total_value")
    private long totalValue;

}
