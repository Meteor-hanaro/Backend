package com.hana.app.service;

import org.springframework.stereotype.Service;

@Service
public class MainService {
    public boolean checkAdminPwdCheck(String inputPwd) {
        return inputPwd.equals("adminpw");
    }
}
