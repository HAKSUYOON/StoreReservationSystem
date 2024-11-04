package com.zerobase.srs.review.controller;

import com.zerobase.srs.review.dto.ReviewDto;
import com.zerobase.srs.review.model.ReviewInput;
import com.zerobase.srs.review.model.ReviewParam;
import com.zerobase.srs.review.sevice.ReviewService;
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
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping("/review")
    public String review(Model model, ReviewParam parameter, Principal principal) {

        String userId = principal.getName();
        parameter.setUserId(userId);

        List<ReviewDto> reviewList = reviewService.list(parameter);

        model.addAttribute("list", reviewList);

        return "review/list";
    }

    @GetMapping("/review/register")
    public String reviewRegister(Model model, Principal principal, ReviewParam parameter) {

        String userId = principal.getName();
        model.addAttribute("userId", userId);
        model.addAttribute("storeId", parameter.getStoreId());
        model.addAttribute("storeName", parameter.getStoreName());
        model.addAttribute("reservationDt", parameter.getReservationDt());

        return "review/register";
    }

    @PostMapping("/review/register")
    public String reviewRegisterSubmit(Model model, HttpServletRequest request, ReviewInput parameter, Principal principal) {

        parameter.setUserId(principal.getName());

        boolean result = reviewService.add(parameter);
        model.addAttribute("result", result);

        return "review/register_complete";
    }

    @PostMapping("/review/delete")
    public String delete(ReviewParam parameter) {

        reviewService.delete(parameter.getId());

        return "redirect:/review";
    }

    @GetMapping("/review/edit")
    public String reviewEdit(Model model, Principal principal, ReviewParam parameter) {

        String userId = principal.getName();
        model.addAttribute("userId", userId);
        model.addAttribute("storeId", parameter.getStoreId());
        model.addAttribute("storeName", parameter.getStoreName());
        model.addAttribute("reservationDt", parameter.getReservationDt());

        return "review/edit";
    }

    @PostMapping("/review/edit")
    public String reviewEditSubmit(Model model, HttpServletRequest request, ReviewInput parameter, Principal principal) {

        parameter.setUserId(principal.getName());

        boolean result = reviewService.update(parameter);
        model.addAttribute("result", result);

        return "review/edit_complete";
    }
}
