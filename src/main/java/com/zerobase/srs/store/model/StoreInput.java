package com.zerobase.srs.store.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class StoreInput {
    private int id;

    private String storeName;

    private String zipcode;
    private String storeAddress;
    private String addressDetail;

    private String storeDetail;

    private String phone;

}
