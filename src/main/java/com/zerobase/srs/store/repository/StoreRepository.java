package com.zerobase.srs.store.repository;

import com.zerobase.srs.store.entity.Store;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface StoreRepository extends JpaRepository<Store, Integer> {

    Optional<Store> findByZipcodeAndStoreAddressAndAddressDetail(String zipcode, String storeAddress, String addressDetail);
}
