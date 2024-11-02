package com.zerobase.srs.reservation.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@Entity
public class Reservation implements ReservationStatus{

    @Id
    private Long Id;

    private String customerId;
    private Long storeId;

    private LocalDateTime reservationDt;
    private String status;
    private boolean usingYn;
}
