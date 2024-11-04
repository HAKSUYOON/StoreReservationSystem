package com.zerobase.srs.review.controller;

import com.zerobase.srs.review.dto.ReviewDto;
import com.zerobase.srs.review.model.ReviewParam;
import com.zerobase.srs.review.sevice.ReviewService;
import com.zerobase.srs.store.sevice.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;

@RequiredArgsConstructor
@Controller
public class PartnerReviewController {

    private final ReviewService reviewService;
    private final StoreService storeService;

    @GetMapping("/partner/review/list")
    public String list(Model model, ReviewParam parameter, Principal principal) {

        parameter.setUserId(principal.getName());


        List<Long> storeIdList = storeService.getMyStoreIds(parameter.getUserId());
        List<ReviewDto> reviewList = reviewService.partnerList(storeIdList);

        model.addAttribute("reviewList", reviewList);

        return "partner/review/list";
    }

}
