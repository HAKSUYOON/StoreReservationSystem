package com.zerobase.srs.store.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StoreInput {
    long id;

    String storeName;

    String zipcode;
    String storeAddress;
    String addressDetail;

    String storeDetail;

    String userId;

}
