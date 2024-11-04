package com.zerobase.srs.partner.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PartnerController {

    /**
     * 파트너 메인페이지
     */
    @GetMapping("/partner/main")
    public String partnerMain() {
        return "partner/main";
    }
}
