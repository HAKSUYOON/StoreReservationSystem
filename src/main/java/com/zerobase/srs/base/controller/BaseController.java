package com.zerobase.srs.base.controller;

import com.zerobase.srs.util.PageUtil;

public class BaseController {

    /**
     * 페이징
     */
    public String getPagerHtml(long totalCount, long pageSize, long pageIndex, String queryString) {

        PageUtil pageUtil = new PageUtil(totalCount, pageSize, pageIndex, queryString);
        return pageUtil.pager();
    }
}
