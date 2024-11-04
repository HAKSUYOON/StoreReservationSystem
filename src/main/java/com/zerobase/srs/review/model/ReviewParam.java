package com.zerobase.srs.review.model;

import lombok.Data;

@Data
public class ReviewParam {

    long id;
    private String userId;

    long storeId;
    String storeName;
    String reservationDt;
}
