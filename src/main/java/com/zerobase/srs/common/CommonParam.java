package com.zerobase.srs.common;

import lombok.Data;

@Data
public class CommonParam {

    long pageIndex;
    long pageSize;

    String orderType;

    public long getPageStart() {
        init();
        return (pageIndex - 1) * pageSize;
    }

    public long getPageEnd() {
        init();
        return pageSize;
    }

    public void init() {
        if (pageIndex < 1) {
            pageIndex = 1;
        }

        if (pageSize < 10) {
            pageSize = 10;
        }
    }

    public String getQueryString() {
        init();

        StringBuilder sb = new StringBuilder();

        if (orderType != null && orderType.length() > 0) {
            sb.append(String.format("orderType=%s", orderType));
        }

        return sb.toString();
    }

}
