package com.hana.controller;

import com.hana.app.service.user.UsersService;
import com.hana.dto.response.PbDto;
import com.hana.dto.response.UsersDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/pb")
@RequiredArgsConstructor
@Slf4j
public class PbController {

    private final UsersService userService;

    @RequestMapping("/login")
    @ResponseBody
    public UsersDto.TokenInfo login(String email, String password) {
        return userService.login(email, password);
    }

    @RequestMapping("/logout")
    @ResponseBody
    public void logout(@RequestHeader("accessToken") String accessToken, @RequestHeader("refreshToken") String refreshToken) {
        userService.logout(accessToken);
    }

    @RequestMapping("/main")
    @ResponseBody
    public PbDto info(@RequestHeader("accessToken") String accessToken, @RequestHeader("refreshToken") String refreshToken) {
        return userService.getVipList(accessToken);
    }

    @RequestMapping("/main/state")
    @ResponseBody
    public PbDto getLoginState(@RequestHeader("accessToken") String accessToken, @RequestHeader("refreshToken") String refreshToken){
        return new PbDto(null, userService.getVipStateList(accessToken));
    }
}
