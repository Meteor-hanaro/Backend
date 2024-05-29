package com.hana.app.service.portfolio;

import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.frame.BaseService;
import com.hana.app.repository.portfolio.PortfolioItemRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioItemService implements BaseService<Long, PortfolioItem> {

    final PortfolioItemRepository portfolioItemRepository;

    @Override
    public PortfolioItem insert(PortfolioItem portfolioItem) {
        return portfolioItemRepository.save(portfolioItem);
    }

    @Override
    public void delete(Long aLong) {
        portfolioItemRepository.deleteById(aLong);
    }

    @Override
    public PortfolioItem update(PortfolioItem portfolioItem) {
        // Front 단에서 객체 신중하게 담기. 나머진 다 유지하고 amount만 바뀌게
        return portfolioItemRepository.save(portfolioItem);
    }

    @Override
    public Optional<PortfolioItem> get(Long aLong) {
        return portfolioItemRepository.findById(aLong);
    }

    @Override
    public List<PortfolioItem> getAll() {
        return portfolioItemRepository.findAll();
    }
}
