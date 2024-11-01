package com.zerobase.srs.store.dto;

import com.zerobase.srs.store.entity.Store;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class StoreDto {

    Long id;

    String storeName;

    String zipcode;
    String storeAddress;
    String addressDetail;

    String storeDetail;

    String userId;

    long totalCount;
    long seq;

    public static StoreDto of (Store store) {

        StoreDto storeDto = StoreDto.builder()
                                    .id(store.getId())
                                    .storeName(store.getStoreName())
                                    .zipcode(store.getZipcode())
                                    .storeAddress(store.getStoreAddress())
                                    .addressDetail(store.getAddressDetail())
                                    .storeDetail(store.getStoreDetail())
                                    .userId(store.getUserId())
                                    .build();

        return storeDto;
    }

    public static List<StoreDto> of (List<Store> stores) {

        if (stores == null) {
            return null;
        }

        List<StoreDto> storeList = new ArrayList<>();
        for (Store x : stores) {
            storeList.add(StoreDto.of(x));
        }
        return storeList;
    }
}
