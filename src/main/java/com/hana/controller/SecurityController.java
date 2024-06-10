package com.hana.controller;

import com.hana.app.service.security.SecurityService;
import com.hana.dto.response.security.SecurityDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/security")
@RequiredArgsConstructor
public class SecurityController {

    final SecurityService securityService;

    @GetMapping("/admin/allSecurities")
    public SecurityDto extractAllSecurityData() {
        return securityService.extractAllSecurityInfo();
    }
}
