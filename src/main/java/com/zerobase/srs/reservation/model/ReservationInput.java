package com.zerobase.srs.reservation.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class ReservationInput {

    private long id;

    private String customerId;
    private String storeId;

    private LocalDateTime reservationDt;
    private String status;
    private String usingYn;
}
