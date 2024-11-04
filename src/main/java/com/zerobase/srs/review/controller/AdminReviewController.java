package com.zerobase.srs.review.controller;

import com.zerobase.srs.review.dto.ReviewDto;
import com.zerobase.srs.review.model.ReviewParam;
import com.zerobase.srs.review.sevice.ReviewService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class AdminReviewController {

    private final ReviewService reviewService;

    /**
     * 관리자 리뷰 관리 목록
     */
    @GetMapping("/admin/review/list.do")
    public String list(Model model, ReviewParam parameter) {

        List<ReviewDto> list = reviewService.listAll(parameter);

        model.addAttribute("list", list);

        return "admin/review/list";
    }

    /**
     * 관리자 리뷰 삭제 POST
     */
    @PostMapping("/admin/review/delete.do")
    public String delete(ReviewParam parameter) {

        boolean result = reviewService.delete(parameter.getId());

        return "redirect:/admin/review/list";
    }
}
