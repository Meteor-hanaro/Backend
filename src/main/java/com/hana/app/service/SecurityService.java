package com.hana.app.service;

import com.hana.app.data.entity.security.Security;
import com.hana.app.repository.security.SecurityRepository;
import com.hana.dto.response.SecurityDto;
import com.hana.dto.response.SecurityItemDto;
import com.hana.exception.MeteorException;
import com.hana.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SecurityService {

    final SecurityRepository securityRepository;

    public SecurityDto extractAllSecurityInfo() {
        return SecurityDto.from(allSecurities());
    }

    private List<SecurityItemDto> allSecurities() {
        List<SecurityItemDto> list = new ArrayList<>();

        try {
            for (Security security : securityRepository.findAll()) {
                list.add(SecurityItemDto.from(security));
            }
        } catch (MeteorException e) {
            throw new NotFoundException(e.getErrorType());
        }

        return list;
    }
}
