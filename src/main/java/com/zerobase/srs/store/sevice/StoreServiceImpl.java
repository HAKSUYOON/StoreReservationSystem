package com.zerobase.srs.store.sevice;

import com.zerobase.srs.store.dto.StoreDto;
import com.zerobase.srs.store.entity.Store;
import com.zerobase.srs.store.mapper.StoreMapper;
import com.zerobase.srs.store.model.StoreInput;
import com.zerobase.srs.store.model.StoreParam;
import com.zerobase.srs.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class StoreServiceImpl implements StoreService {

    private final StoreRepository storeRepository;
    private final StoreMapper storeMapper;

    @Override
    public boolean register(StoreInput parameter) {

        Optional<Store> optionalStore = storeRepository.findByZipcodeAndStoreAddressAndAddressDetail(parameter.getZipcode(), parameter.getStoreAddress(), parameter.getAddressDetail());
        if (optionalStore.isPresent()) {
            return false;
        }

        Store store = Store.builder()
                           .storeName(parameter.getStoreName())
                           .zipcode(parameter.getZipcode())
                           .storeAddress(parameter.getStoreAddress())
                           .addressDetail(parameter.getAddressDetail())
                           .storeDetail(parameter.getStoreDetail())
                           .userId(parameter.getUserId())
                           .build();
        storeRepository.save(store);

        return true;
    }

    @Override
    public List<StoreDto> list(StoreParam parameter) {

        long totalCount = storeMapper.selectListCount(parameter);
        List<StoreDto> list = storeMapper.selectList(parameter);
        if (!CollectionUtils.isEmpty(list)) {
            int i = (int)(totalCount - parameter.getPageStart() - 1);
            for (StoreDto x : list) {
                x.setTotalCount(totalCount);
                x.setSeq(totalCount - parameter.getPageStart() - i);
                i--;
            }
        }

        return list;
    }
}
