package com.zerobase.srs.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AdminController {

    @GetMapping("/admin/main.do")
    public String adminMain() {
        return "admin/main";
    }
}
