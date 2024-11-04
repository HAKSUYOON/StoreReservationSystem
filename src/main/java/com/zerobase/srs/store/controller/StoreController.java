package com.zerobase.srs.store.controller;

import com.zerobase.srs.base.controller.BaseController;
import com.zerobase.srs.member.service.MemberService;
import com.zerobase.srs.store.dto.StoreDto;
import com.zerobase.srs.store.model.StoreParam;
import com.zerobase.srs.store.sevice.StoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class StoreController extends BaseController {

    private final StoreService storeService;
    private final MemberService memberService;

    /**
     * 회원 가게 목록
     */
    @GetMapping("/store/list")
    public String store(Model model, StoreParam parameter) {

        parameter.init();

        List<StoreDto> storeList = storeService.list(parameter);

        long totalCount = 0;
        if (!CollectionUtils.isEmpty(storeList)) {
            totalCount = storeList.get(0).getTotalCount();
        }
        String queryString = parameter.getQueryString();
        String pagerHtml = getPagerHtml(totalCount, parameter.getPageSize(), parameter.getPageIndex(), queryString);

        model.addAttribute("list", storeList);
        model.addAttribute("totalCount", totalCount);
        model.addAttribute("pager", pagerHtml);

        return "store/list";
    }

    /**
     * 회원 가게 상세페이지
     */
    @GetMapping("/store/{id}")
    public String detail(Model model, StoreParam parameter) {

        StoreDto detail = storeService.detail(parameter.getId());
        String partnerPhone = memberService.getPhone(detail.getUserId());
        model.addAttribute("detail", detail);
        model.addAttribute("partnerPhone", partnerPhone);

        return "store/detail";
    }

}
