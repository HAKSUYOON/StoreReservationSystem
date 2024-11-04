package com.zerobase.srs.reservation.dto;

import com.zerobase.srs.reservation.entity.Reservation;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class ReservationDto {

    private Long id;

    private String customerId;
    private Long storeId;
    private String storeName;

    private LocalDateTime reservationDt;
    private String status;
    private boolean usingYn;

    long totalCount;
    long seq;

    public static ReservationDto of (Reservation reservation) {

        ReservationDto reservationDto = ReservationDto.builder()
                                                      .id(reservation.getId())
                                                      .customerId(reservation.getCustomerId())
                                                      .storeId(reservation.getStoreId())
                                                      .reservationDt(reservation.getReservationDt())
                                                      .status(reservation.getStatus())
                                                      .usingYn(reservation.isUsingYn())
                                                      .build();

        return reservationDto;
    }

    public static List<ReservationDto> of (List<Reservation> reservations) {

        if (reservations == null) {
            return null;
        }

        List<ReservationDto> reservationList = new ArrayList<>();
        for (Reservation x : reservations) {
            reservationList.add(ReservationDto.of(x));
        }
        return reservationList;
    }
}
