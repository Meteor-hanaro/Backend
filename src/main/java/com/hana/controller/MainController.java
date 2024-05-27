package com.hana.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("/api")
public class MainController {

    @ResponseBody
    @RequestMapping("")
    public String main() {
        return "hello meteor";
    }
}
