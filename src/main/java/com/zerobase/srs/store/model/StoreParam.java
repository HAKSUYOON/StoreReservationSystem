package com.zerobase.srs.store.model;

import com.zerobase.srs.common.CommonParam;
import lombok.Data;

@Data
public class StoreParam extends CommonParam {

    long id;
    String storeName;
}
