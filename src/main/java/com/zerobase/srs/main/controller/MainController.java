package com.zerobase.srs.main.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class MainController {

    /**
     * 메인 페이지
     */
    @RequestMapping("/")
    public String index() {
        return "index";
    }

    /**
     * 권한 에러페이지
     */
    @RequestMapping("/error/denied")
    public String errorDenied() {
        return "error/denied";
    }
}
