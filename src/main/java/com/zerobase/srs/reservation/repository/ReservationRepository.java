package com.zerobase.srs.reservation.repository;

import com.zerobase.srs.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {


    Optional<List<Reservation>> findByCustomerId(String userId);

    Optional<Reservation> findByCustomerIdAndStoreIdAndReservationDt(String customerId, Long storeId, LocalDateTime ReservationDt);

    Optional<List<Reservation>> findByStoreId(Long storeId);
}
