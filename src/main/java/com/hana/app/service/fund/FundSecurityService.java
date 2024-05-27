package com.hana.app.service.fund;

import com.hana.app.data.entity.fund.FundSecurity;
import com.hana.app.frame.BaseService;
import com.hana.app.repository.fund.FundSecurityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FundSecurityService implements BaseService<Long, FundSecurity> {

    final FundSecurityRepository fundSecurityRepository;

    @Override
    public FundSecurity insert(FundSecurity fundSecurityService) {
        // FundSecurity에 대해 삽입 기능 제공하지 않음
        return null;
    }

    @Override
    public void delete(Long aLong) {
        // FundSecurity에 대해 삭제 기능 제공하지 않음
    }

    @Override
    public FundSecurity update(FundSecurity fundSecurityService) {
        // FundSecurity에 대해 수정 기능 제공하지 않음
        return null;
    }

    @Override
    public Optional<FundSecurity> get(Long aLong) {
        return fundSecurityRepository.findById(aLong);
    }

    @Override
    public List<FundSecurity> getAll() {
        return fundSecurityRepository.findAll();
    }
}
