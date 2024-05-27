package com.hana.app.service.fund;

import com.hana.app.data.entity.fund.Fund;
import com.hana.app.frame.BaseService;
import com.hana.app.repository.fund.FundRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class FundService implements BaseService<Long, Fund> {

    final FundRepository fundRepository;

    @Override
    public Fund insert(Fund fund) {
        // Fund에 insert 기능은 제공되지 않는다.
        // return fundRepository.save(fund);
        return null;
    }

    @Override
    public void delete(Long aLong) {
        // Fund에 delete 기능은 제공되지 않는다.
    }

    @Override
    public Fund update(Fund fund) {
        // Fund에 update 기능은 제공되지 않는다.
        return null;
    }

    @Override
    public Optional<Fund> get(Long aLong) {
        return fundRepository.findById(aLong);
    }

    @Override
    public List<Fund> getAll() {
        return fundRepository.findAll();
    }
}
