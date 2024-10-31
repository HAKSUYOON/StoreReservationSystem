package com.zerobase.srs.store.controller;

import com.zerobase.srs.base.controller.BaseController;
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

    @GetMapping("/store/{id}")
    public String detail() {
        return "store/detail";
    }

}
