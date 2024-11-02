package com.zerobase.srs.reservation.service;

import com.zerobase.srs.reservation.dto.ReservationDto;
import com.zerobase.srs.reservation.entity.Reservation;
import com.zerobase.srs.reservation.model.ReservationInput;
import com.zerobase.srs.reservation.model.ReservationParam;
import com.zerobase.srs.reservation.repository.ReservationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;

    private LocalDateTime getLocalDate(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH");
        try {
            return LocalDateTime.parse(value, formatter);
        } catch (Exception e) {

        }
        return null;
    }

    @Override
    public List<ReservationDto> list(ReservationParam parameter) {

        Optional<List<Reservation>> optionalReservations = reservationRepository.findByCustomerId(parameter.getUserId());
        if (optionalReservations.isEmpty()) {
            return null;
        }
        return ReservationDto.of(optionalReservations.get());
    }

    @Override
    public boolean add(ReservationInput parameter) {

        Optional<Reservation> optionalReservation = reservationRepository.findByCustomerIdAndStoreIdAndReservationDt(parameter.getCustomerId(), parameter.getStoreId(), getLocalDate(parameter.getReservationDtText()));
        if (optionalReservation.isPresent()) {
            return false;
        }

        LocalDateTime reservationDt = getLocalDate(parameter.getReservationDtText());

        Reservation reservation = Reservation.builder()
                .customerId(parameter.getCustomerId())
                .storeId(parameter.getStoreId())
                .reservationDt(reservationDt)
                .status(Reservation.RESERVATION_STATUS_REQ)
                .usingYn(false)
                .build();

        return true;
    }
}
