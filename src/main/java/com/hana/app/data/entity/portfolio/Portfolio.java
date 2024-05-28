package com.hana.app.data.entity.portfolio;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.User;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="portfolio")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
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
