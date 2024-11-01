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
public class Reservation {

    @Id
    private Long Id;

    private String customerId;
    private String storeId;

    private LocalDateTime ReservationDt;
    private String status;
    private boolean usingYn;
}
