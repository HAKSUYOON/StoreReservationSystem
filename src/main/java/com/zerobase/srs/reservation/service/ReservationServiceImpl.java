package com.zerobase.srs.reservation.service;

import com.zerobase.srs.reservation.dto.ReservationDto;
import com.zerobase.srs.reservation.entity.Reservation;
import com.zerobase.srs.reservation.model.ReservationInput;
import com.zerobase.srs.reservation.model.ReservationParam;
import com.zerobase.srs.reservation.repository.ReservationRepository;
import com.zerobase.srs.store.entity.Store;
import com.zerobase.srs.store.repository.StoreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepository reservationRepository;
    private final StoreRepository storeRepository;

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
        reservationRepository.save(reservation);

        return true;
    }

    @Override
    public List<ReservationDto> partnerList(ReservationParam parameter) {

        List<Reservation> reservationList = new ArrayList<>();

        Optional<List<Store>> optionalStores = storeRepository.findByUserId(parameter.getUserId());
        if (optionalStores.isEmpty()) {
            return null;
        }

        for (Store x : optionalStores.get()) {
            Long storeId = x.getId();
            Optional<List<Reservation>> optionalReservations = reservationRepository.findByStoreId(storeId);
            if (optionalReservations.isEmpty()) {
                return null;
            }
            for (Reservation y : optionalReservations.get()) {
                reservationList.add(y);
            }
        }
        return ReservationDto.of(reservationList);
    }

    @Override
    public boolean approved(long id) {

        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isEmpty()) {
            return false;
        }

        Reservation reservation = optionalReservation.get();
        reservation.setStatus(Reservation.RESERVATION_STATUS_APPROVED);
        reservationRepository.save(reservation);

        return true;
    }

    @Override
    public boolean canceled(long id) {

        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isEmpty()) {
            return false;
        }

        Reservation reservation = optionalReservation.get();
        reservation.setStatus(Reservation.RESERVATION_STATUS_CANCEL);
        reservationRepository.save(reservation);

        return true;
    }

    @Override
    public boolean use(long id) {

        Optional<Reservation> optionalReservation = reservationRepository.findById(id);
        if (optionalReservation.isEmpty()) {
            return false;
        }

        Reservation reservation = optionalReservation.get();
        reservation.setUsingYn(true);
        reservationRepository.save(reservation);

        return true;
    }
}
