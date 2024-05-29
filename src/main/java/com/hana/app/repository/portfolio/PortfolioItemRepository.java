package com.hana.app.repository.portfolio;

import com.hana.app.data.entity.portfolio.PortfolioItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PortfolioItemRepository extends JpaRepository<PortfolioItem, Long> {
    List<PortfolioItem> findAllByPortfolioId(Long portfolioId);
}
