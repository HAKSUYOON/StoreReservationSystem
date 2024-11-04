package com.zerobase.srs.store.sevice;

import com.zerobase.srs.store.dto.StoreDto;
import com.zerobase.srs.store.model.StoreInput;
import com.zerobase.srs.store.model.StoreParam;

import java.util.List;

public interface StoreService {

    /**
     * 상점 등록
     */
    boolean register(StoreInput parameter);

    /**
     * 상정 목록
     */
    List<StoreDto> list(StoreParam parameter);

    /**
     * 상점 상세정보
     */
    StoreDto detail(long id);

    /**
     * 상점명 가져오기
     */
    String getStoreName(long storeId);

    /**
     * 파트너 등록상점 id 가져오기
     */
    List<Long> getMyStoreIds(String userId);
}
