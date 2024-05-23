package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="suggestion_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SuggestionItem extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "suggestion_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggestion_id", nullable = false)
    private Suggestion suggestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id", nullable = false)
    private Fund fund;

    @Column(nullable = false, name = "fund_value")
    private Long fundValue;
}
