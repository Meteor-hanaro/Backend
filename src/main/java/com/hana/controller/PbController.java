package com.hana.controller;

import com.hana.app.service.user.PbService;
import com.hana.app.service.user.UsersService;
import com.hana.dto.request.PbPwdCheckDto;
import com.hana.dto.response.PbDto;
import com.hana.dto.response.UsersDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/pb")
@RequiredArgsConstructor
@Slf4j
public class PbController {

    private final UsersService userService;
    private final PbService pbService;

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
        return new PbDto(userService.getVipList(accessToken), userService.getVipStateList(accessToken));
    }

    @RequestMapping("/main/state")
    @ResponseBody
    public PbDto getLoginState(@RequestHeader("accessToken") String accessToken, @RequestHeader("refreshToken") String refreshToken, @RequestBody PbDto requestData){
        return new PbDto(null, userService.getVipStateList(requestData.getVip()));
    }

    @RequestMapping("/main/filter")
    @ResponseBody
    public PbDto filter(@RequestHeader("accessToken") String accessToken, @RequestHeader("refreshToken") String refreshToken, String riskType, String name){
        List<PbDto.VipInfo> vipInfoList = userService.getVipListByFilter(accessToken, riskType, name);
        List<PbDto.VipState> vipStateList = userService.getVipStateList(vipInfoList);
        return new PbDto(vipInfoList, vipStateList);
    }

    @PostMapping("/main/pwdcheck")
    public Boolean isAuthenticated(@RequestBody PbPwdCheckDto pbPwdCheckDto) {
        return pbService.isPbAuthenticated(pbPwdCheckDto);
    }
}
