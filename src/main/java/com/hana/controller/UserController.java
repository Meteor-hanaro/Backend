package com.hana.controller;

import com.hana.app.service.VipService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserController {
    final VipService vipService;

    @RequestMapping("/name")
    public String getUserNameByVipId(Long vipId) {
        return vipService.getVipByVipId(vipId).getUser().getName();
    }
}
