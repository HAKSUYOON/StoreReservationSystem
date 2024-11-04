package com.zerobase.srs.review.model;

import lombok.Data;
import lombok.ToString;

@ToString
@Data
public class ReviewInput {

    long id;

    private String userId;

    long storeId;
    String storeName;
    String reservationDt;

    String title;

    String contents;

    long score;
}
