package com.hana;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Slf4j
public class PasswordEncoder {

    private static final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static void main(String[] args) {
        // 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode("gawonpw");
        log.info(encodedPassword);
    }
}
