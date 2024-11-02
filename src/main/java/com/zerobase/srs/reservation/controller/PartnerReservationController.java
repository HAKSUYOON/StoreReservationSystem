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
public class PartnerReservationController {

    private final ReservationService reservationService;
    private final StoreService storeService;

    @GetMapping("/partner/store/reservation/list")
    public String partnerReservation(Model model, ReservationParam parameter, Principal principal) {

        parameter.setUserId(principal.getName());

        List<ReservationDto> list = reservationService.partnerList(parameter);
        for (ReservationDto x : list) {
            x.setStoreName(storeService.getStoreName(x.getStoreId()));
        }
        model.addAttribute("list", list);

        return "partner/reservation/list";
    }

    @PostMapping("/partner/store/reservation/list")
    public String setApproveYn(Model model, HttpServletRequest request, ReservationInput parameter, Principal principal) {

        return "redirect:/partner/store/reservation/list";
    }
}
