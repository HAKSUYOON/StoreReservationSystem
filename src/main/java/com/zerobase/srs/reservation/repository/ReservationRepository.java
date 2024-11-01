package com.zerobase.srs.reservation.repository;

import com.zerobase.srs.reservation.entity.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {
}
