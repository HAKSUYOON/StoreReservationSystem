package com.zerobase.srs.store.controller;

import com.zerobase.srs.store.model.StoreInput;
import com.zerobase.srs.store.sevice.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;

@RequiredArgsConstructor
@Controller
public class PartnerStoreController {

    private final StoreService storeService;

    @GetMapping("/partner/store/register")
    public String storeRegister() {

        return "partner/store/register";
    }

    @PostMapping("/partner/store/register")
    public String storeRegisterSubmit(Model model, HttpServletRequest request, StoreInput parameter) {

        boolean result = storeService.register(parameter);
        model.addAttribute("result", result);

        return "partner/store/register_complete";
    }
}
