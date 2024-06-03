package com.hana.controller;

import com.hana.app.service.UsersService;
import com.hana.app.service.VipService;
import com.hana.dto.response.UsersDto;
import com.hana.dto.response.VipDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/vip")
@RequiredArgsConstructor
@Slf4j
public class VipController {
    private final UsersService userService;
    private final VipService vipService;

    @RequestMapping("/login")
    @ResponseBody
    public UsersDto.TokenInfo login(String email, String password) {
        return userService.login(email, password);
    }

    @RequestMapping("/main")
    @ResponseBody
    public VipDto info(@RequestHeader("accessToken") String accessToken, @RequestHeader("refreshToken") String refreshToken) {
        return userService.getVipMainInfo(accessToken);
    }

    @PostMapping("/main/pwdcheck")
    public Boolean isAuthenticated(@RequestBody Map<String, String> requestData) {
        String password = requestData.get("pwd");
        String writtenPwd = requestData.get("writtenPwd");

        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        if (passwordEncoder.matches(writtenPwd, password)) {
            return true;
        }else{
            return false;
        }
    }

    @RequestMapping("/name")
    public String getUserNameByVipId(Long vipId) {
        return vipService.getVipByVipId(vipId).getUser().getName();
    }
}
