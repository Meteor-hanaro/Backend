package com.hana.app.service;

import com.hana.app.data.entity.BaseEntity;
import com.hana.app.data.entity.Consult;
import com.hana.app.repository.ConsultRepository;
import com.hana.app.repository.user.PbRepository;
import com.hana.app.repository.user.VipRepository;
import com.hana.dto.request.ConsultRegisterDto;
import com.hana.dto.response.consult.ConsultAdminDto;
import com.hana.dto.response.consult.ConsultAdminItemDto;
import com.hana.dto.response.consult.ConsultSearchDto;
import com.hana.dto.response.consult.ConsultWebRTCRoomDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultService {

    final ConsultRepository consultRepository;
    final VipRepository vipRepository;
    final PbRepository pbRepository;

    public void registerConsult(ConsultRegisterDto consultRegisterDto) {
        consultRepository.save(makeConsult(consultRegisterDto));
    }

    public ConsultWebRTCRoomDto findActiveConsultByVipId(Long userId) {
        if (findActiveConsult(userId) == null) return null;
        return new ConsultWebRTCRoomDto(findActiveConsult(userId));
    }

    public ConsultSearchDto searchConsultIsExist(Long pbId) {
        return new ConsultSearchDto(searchPbVipConsultIsExist(pbId));
    }

    public ConsultAdminDto extractAdminConsultData(){
        return new ConsultAdminDto(extractAllConsultData());
    }

    private Consult makeConsult(ConsultRegisterDto consultRegisterDto) {
        Consult consult = null;

        try {
            consult = Consult.builder()
                    .vip(vipRepository.findById(consultRegisterDto.getVipId()).orElse(null))
                    .pb(pbRepository.findById(consultRegisterDto.getPbId()).orElse(null))
                    .content(consultRegisterDto.getContent())
                    .startedAt(LocalDateTime.now())
                    .build();
        } catch (MeteorException e) {
            throw new RuntimeException(e.getMessage());
        }

        return consult;
    }

    private Consult findActiveConsult(Long userId) {
        Consult consult = null;

        try {
            List<Consult> byVipId = consultRepository.findByVipId(userId);

            for (Consult c : byVipId) {
                if(c.getStatus() != null && c.getStatus().equals(BaseEntity.BaseState.ACTIVE)) {
                    consult = c;
                    break;
                }
            }

        } catch (MeteorException e) {
            throw new NotFoundException(e.getErrorType());
        }

        return consult;
    }

    private List<ConsultWebRTCRoomDto> searchPbVipConsultIsExist(Long pbId) {
        List<ConsultWebRTCRoomDto> list = new ArrayList<>();

        try {
            for (Long id : consultRepository.findByPbId(pbId)) {
                if(findActiveConsult(id) != null) list.add(new ConsultWebRTCRoomDto(findActiveConsult(id)));
            }
        } catch (MeteorException e) {
            throw new RuntimeException();
        }

        return list;
    }

    private List<ConsultAdminItemDto> extractAllConsultData() {
        List<ConsultAdminItemDto> list = new ArrayList<>();

        try {
            for (Consult consult : consultRepository.findAll()) {
                if (consult.getStatus() == BaseEntity.BaseState.ACTIVE) {
                    list.add(ConsultAdminItemDto.from(consult));
                }
            }
        } catch (MeteorException e) {
            throw new NotFoundException(e.getErrorType());
        }

        return list;
    }
}
