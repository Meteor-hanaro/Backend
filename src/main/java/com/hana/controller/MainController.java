package com.hana.controller;

import com.hana.app.service.MainService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MainController {

    final MainService mainService;

//    @RequestMapping("")
//    public String main() {
//        return "hello meteor";
//    }

    @GetMapping("/admin/pwdCheck")
    public boolean adminPwdCheck(@RequestParam("inputPwd") String inputPwd) {
        return mainService.checkAdminPwdCheck(inputPwd);
    }
}
