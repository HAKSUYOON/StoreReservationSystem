package com.zerobase.srs.reservation.model;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

@ToString
@Data
public class ReservationInput {

    private long id;

    private String customerId;
    private Long storeId;

    private String reservationDtText;
    private String status;
    private String usingYn;
}
