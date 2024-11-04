package com.zerobase.srs.reservation.controller;

import com.zerobase.srs.reservation.dto.ReservationDto;
import com.zerobase.srs.reservation.model.ReservationInput;
import com.zerobase.srs.reservation.model.ReservationParam;
import com.zerobase.srs.reservation.service.ReservationService;
import com.zerobase.srs.store.sevice.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class ReservationController {

    private final ReservationService reservationService;
    private final StoreService storeService;

    @GetMapping("/kiosk")
    public String reservation(Model model, ReservationParam parameter, Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        List<ReservationDto> reservationList = reservationService.list(parameter);
        for (ReservationDto x : reservationList) {
            x.setStoreName(storeService.getStoreName(x.getStoreId()));
        }
        model.addAttribute("list", reservationList);

        return "kiosk/list";
    }

    @PostMapping("/reservation/add")
    public String reservationSubmit(Model model, HttpServletRequest request, ReservationInput parameter, Principal principal) {

        parameter.setCustomerId(principal.getName());

        boolean result = reservationService.add(parameter);
        model.addAttribute("result", result);

        return "kiosk/reservation_complete";

    }

    @PostMapping("/kiosk/use")
    public String setUsingY(Model model, HttpServletRequest request, ReservationInput parameter) {

        boolean result = reservationService.use(parameter.getId());
        model.addAttribute("result", result);

        return "redirect:/kiosk";
    }
}
