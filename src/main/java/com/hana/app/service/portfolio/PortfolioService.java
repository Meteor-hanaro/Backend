package com.hana.app.service.portfolio;

import com.hana.app.data.entity.portfolio.Portfolio;
import com.hana.app.frame.BaseService;
import com.hana.app.repository.portfolio.PortfolioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PortfolioService implements BaseService<Long, Portfolio> {

    final PortfolioRepository portfolioRepository;

    @Override
    public Portfolio insert(Portfolio portfolio) {
        // 새로운 포폴을 삽입할 경우가 발생하지 않을 것이라 보고 구현하지 않음
        return null;
    }

    @Override
    public void delete(Long aLong) {
        // 손님의 포폴이 삭제될 일이 없을 것이라 보고 구현하지 않음.
    }

    @Override
    public Portfolio update(Portfolio portfolio) {
        // 포폴의 메타 데이터에 변화를 주지 않을 것이므로 구현하지 않음.
        return null;
    }

    @Override
    public Optional<Portfolio> get(Long aLong) {
        return portfolioRepository.findById(aLong);
    }

    @Override
    public List<Portfolio> getAll() {
        // 다른 손님의 포폴은 가져오지 않을 것이므로 구현하지 않음.
        return List.of();
    }
}
