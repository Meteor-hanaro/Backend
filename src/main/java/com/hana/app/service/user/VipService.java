package com.hana.app.service.user;

import com.hana.app.data.entity.VIP;
import com.hana.app.repository.VipRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class VipService {
    final VipRepository vipRepository;

    public VIP getVipByVipId(Long vipId) {
        return vipRepository.findById(vipId).get();
    }
}
