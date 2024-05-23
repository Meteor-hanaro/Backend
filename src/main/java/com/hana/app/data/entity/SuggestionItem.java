package com.hana.app.data.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="suggestion_item")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class SuggestionItem extends BaseEntity {
    //    index 추가
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(nullable = false, name = "suggestion_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "suggestion_id")
//    @Column(nullable = false, columnDefinition = "LONG", name = "suggestion_id")
    private Suggestion suggestion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "fund_id")
//    @Column(nullable = false, columnDefinition = "LONG", name = "fund_id")
    private Fund fund;

    @Column(nullable = false, name = "fund_value")
    private Long fundValue;
}
