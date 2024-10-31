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
}
