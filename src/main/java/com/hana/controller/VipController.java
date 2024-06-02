package com.hana.controller;

import com.hana.app.service.UsersService;
import com.hana.dto.response.UsersDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/vip")
@RequiredArgsConstructor
@Slf4j
public class VipController {
    private final UsersService userService;

    @RequestMapping("/login")
    @ResponseBody
    public UsersDto.TokenInfo login(String email, String password) {
        return userService.login(email, password);
    }

}