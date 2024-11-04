package com.zerobase.srs.store.controller;

import com.zerobase.srs.store.model.StoreInput;
import com.zerobase.srs.store.sevice.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;

@RequiredArgsConstructor
@Controller
public class PartnerStoreController {

    private final StoreService storeService;

    /**
     * 파트너 가게 등록 GET
     */
    @GetMapping("/partner/store/register")
    public String storeRegister(Model model, Principal principal) {

        String userId = principal.getName();
        model.addAttribute("userId",userId);

        return "partner/store/register";
    }

    /**
     * 파트너 가게 등록 POST
     */
    @PostMapping("/partner/store/register")
    public String storeRegisterSubmit(Model model, HttpServletRequest request, StoreInput parameter, Principal principal) {

        parameter.setUserId(principal.getName());

        boolean result = storeService.register(parameter);
        model.addAttribute("result", result);

        return "partner/store/register_complete";
    }
}
