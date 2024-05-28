package com.hana.app.data.entity.suggestion;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.portfolio.Portfolio;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="suggestion")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Suggestion extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "suggestion_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="portfolio_id")
    private Portfolio portfolio;

    @Column(nullable = false, name = "suggestion_name")
    private String name;
}
