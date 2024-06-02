package com.hana.app.service;

import com.hana.app.data.entity.security.SecurityPrice;
import com.hana.app.repository.security.SecurityPriceRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class SecurityPriceService {
    final SecurityPriceRepository securityPriceRepository;

    public List<SecurityPrice> getPriceHistory(String security_id, LocalDateTime startDate, LocalDateTime endDate) {
        return securityPriceRepository.findBySecurityIdAndTradeDateBetween(security_id, startDate, endDate);
    }
}
