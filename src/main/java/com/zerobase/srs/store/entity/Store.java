package com.zerobase.srs.store.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data
@Entity
public class Store {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String storeName;

    String zipcode;
    String storeAddress;
    String addressDetail;

    @Lob
    String storeDetail;

    // 등록자 id
    String userId;

    // 별점
    long score;
}
