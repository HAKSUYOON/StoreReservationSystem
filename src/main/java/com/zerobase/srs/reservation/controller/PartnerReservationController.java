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

    /**
     * 파트너 예약목록 (본인 가게 예약만 표시)
     */
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

    /**
     * 파트너 예약 승인 POST
     */
    @PostMapping("/partner/store/reservation/approve")
    public String setApproveY(Model model, HttpServletRequest request, ReservationInput parameter) {

        boolean result = reservationService.approved(parameter.getId());
        model.addAttribute("result", result);

        return "redirect:/partner/store/reservation/list";
    }

    /**
     * 파트너 예약 취소 POST
     */
    @PostMapping("/partner/store/reservation/cancel")
    public String setApproveN(Model model, HttpServletRequest request, ReservationInput parameter) {

        boolean result = reservationService.canceled(parameter.getId());

        return "redirect:/partner/store/reservation/list";
    }

}
