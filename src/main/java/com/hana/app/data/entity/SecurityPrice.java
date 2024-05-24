package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "security_price")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class SecurityPrice extends BaseEntity {
    @Id
    @Column(name = "security_price_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
  
    @ManyToOne
    @JoinColumn(name = "security_id", nullable = false)
    private Security security;

    @Column(nullable = false, name="trade_date")
    private LocalDateTime tradeDate;

    @Column(nullable = false, name="trade_price")
    private Long tradePrice;

}
