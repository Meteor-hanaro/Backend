package com.hana.controller;

import com.hana.app.service.ConsultService;
import com.hana.dto.request.ConsultRegisterDto;
import com.hana.dto.response.ConsultWebRTCRoomDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consult")
@RequiredArgsConstructor
public class ConsultController {

    final ConsultService consultService;

    @PostMapping("/registerConsult")
    public void registerConsult(@RequestBody ConsultRegisterDto consultRegisterDto) {
        // 상담 ID = 화상상담방 ID가 되므로 이것을 어떻게 하면 멋있게 설정할 수가 있을까
        try {
            consultService.registerConsult(consultRegisterDto);
        } catch(MeteorException e) {
            throw new RuntimeException(e.getMessage());
        }
    }

    @GetMapping("/extractRTCRoom")
    public ConsultWebRTCRoomDto extractConsultWebRTCRoom(@RequestParam("vipId") Long userId) {
        ConsultWebRTCRoomDto consultWebRTCRoomDto = null;

        try {
            consultWebRTCRoomDto = consultService.findActiveConsultByVipId(userId);
        } catch (MeteorException e) {
            throw new NotFoundException(e.getErrorType());
        }

        return consultWebRTCRoomDto;
    }
}
