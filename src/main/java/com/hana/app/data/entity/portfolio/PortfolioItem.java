package com.hana.app.data.entity.portfolio;

import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.fund.Fund;
import com.hana.app.data.entity.suggestion.SuggestionItem;

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

    @Column(name = "evaluation_amount")
    private Long evaluationAmount;

    @Builder
    public PortfolioItem(Portfolio portfolio, Fund fund, Long startAmount) {
        this.portfolio = portfolio;
        this.fund = fund;
        this.startAmount = startAmount;
        this.evaluationAmount = 0L;
    }

    public void makeInactive() {
        super.makeInactive();
    }

    public static PortfolioItem toPortfolioItem(Portfolio portfolio, SuggestionItem suggestionItem) {
        return PortfolioItem
            .builder()
            .portfolio(portfolio)
            .fund(suggestionItem.getFund())
            .startAmount(suggestionItem.getFundValue())
            .build();
    }
}
