package com.hana.app.service;

import com.hana.app.data.entity.portfolio.Portfolio;
import com.hana.app.data.entity.portfolio.PortfolioItem;
import com.hana.app.data.entity.security.SecurityPrice;
import com.hana.app.repository.UsersRepository;
import com.hana.app.repository.portfolio.PortfolioItemRepository;
import com.hana.app.repository.portfolio.PortfolioRepository;
import com.hana.dto.response.PortfolioDto;
import com.hana.dto.response.PortfolioGraphDto;
import com.hana.dto.response.PortfolioItemDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import com.hana.response.ErrorType;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class PortfolioService {

    final PortfolioRepository portfolioRepository;
    final PortfolioItemRepository portfolioItemRepository;
    final UsersRepository usersRepository;

    //    PortfolioItemResponseDto에 담아서 portfolio view에 보이기.
    public PortfolioDto getPortfolioByVipId(Long vipId) {
        PortfolioDto portfolioDto = null;

        try {
            Portfolio portfolio = portfolioRepository.findPortfolioByVipId(vipId);
            List<PortfolioItemDto> portfolioItems = getPortfolioItems(portfolioItemRepository.findAllByPortfolioId(portfolio.getId()));
            portfolioDto = PortfolioDto.from(portfolio, portfolioItems);
        } catch (MeteorException e) {
            throw new NotFoundException(ErrorType.NOT_FOUND);
        }

        return portfolioDto;
    }

    private List<PortfolioItemDto> getPortfolioItems(List<PortfolioItem> portfolioItems) {
        List<PortfolioItemDto> list = new ArrayList<>();

        for (PortfolioItem portfolioItem : portfolioItems) {
            list.add(PortfolioItemDto.from(portfolioItem));
        }

        return list;
    }

    public LocalDateTime getEarliestCreatedAt(List<PortfolioItemDto> portfolioItemDtos) {
        return portfolioItemDtos.stream().map(PortfolioItemDto::getCreatedAt).min(Comparator.naturalOrder()).orElse(null); // Return null if the list is empty
    }

    public PortfolioGraphDto fillPriceHistory(String security_code, List<SecurityPrice> priceHistory, LocalDateTime earliestDate, LocalDateTime startDate) {
        // Create a map from date to price for quick lookup
        Map<LocalDateTime, Long> priceMap = new HashMap<>();
        for (SecurityPrice sp : priceHistory) {
            priceMap.put(sp.getTradeDate().truncatedTo(ChronoUnit.DAYS), sp.getTradePrice());
        }

        LocalDateTime currentDate = earliestDate.truncatedTo(ChronoUnit.DAYS);
        long lastPrice = 0L;

        List<Long> priceList = new ArrayList<>();
        List<String> dateList = new ArrayList<>();

        LocalDateTime today = LocalDateTime.now().truncatedTo(ChronoUnit.DAYS);


        while (!currentDate.isAfter(today)) {
            if (currentDate.isBefore(startDate)) {
                // Start date 이전에는 0을 추가
                priceList.add(0L);
            } else {
                // Start date 이후에는 priceMap에서 가져오거나 lastPrice를 사용
                if (priceMap.containsKey(currentDate)) {
                    lastPrice = priceMap.get(currentDate);
                }
                priceList.add(lastPrice);
            }
            dateList.add(currentDate.getYear() + "-" + currentDate.getMonthValue() + "-" + currentDate.getDayOfMonth());
            currentDate = currentDate.plusDays(1);
        }

        return PortfolioGraphDto.from(security_code, dateList, priceList);
    }

    public PortfolioGraphDto assemblePriceHistory(String fundName, List<PortfolioGraphDto> portfolioGraphDtos) {
        int serial_length = portfolioGraphDtos.get(0).getSerialValue().size();
        List<Long> new_serial_value = new ArrayList<>();
        List<String> new_serial_date = portfolioGraphDtos.get(0).getSerialTime();

        for (int i = 0; i < serial_length; i++) {

            long dummy = 0L;
            for (PortfolioGraphDto portfolioGraphDto : portfolioGraphDtos) {
                dummy += portfolioGraphDto.getSerialValue().get(i);
            }
            new_serial_value.add(dummy);
        }

        return PortfolioGraphDto.from(fundName, new_serial_date, new_serial_value);
    }

    @Transactional
    public void makePortfolioItemInactive(Long portfolioId) {
        List<PortfolioItem> portfolioItems = portfolioItemRepository.findAllByPortfolioId(portfolioId);
        portfolioItems.stream().forEach(portfolioItem -> portfolioItem.makeInactive());
    }

    public Portfolio findByPortfolioByVipId(Long vipId) {
        return portfolioRepository.findPortfolioByVipId(vipId);
    }

    @Transactional
    public void savePortfolioItems(List<PortfolioItem> portfolioItems) {
        portfolioItemRepository.saveAll(portfolioItems);
    }
}
