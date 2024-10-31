package com.zerobase.srs.store.mapper;

import com.zerobase.srs.store.dto.StoreDto;
import com.zerobase.srs.store.model.StoreParam;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface StoreMapper {
    long selectListCount(StoreParam parameter);

    List<StoreDto> selectList(StoreParam parameter);
}
