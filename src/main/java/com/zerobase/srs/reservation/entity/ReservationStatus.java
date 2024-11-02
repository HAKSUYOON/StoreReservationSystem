package com.zerobase.srs.reservation.entity;

public interface ReservationStatus {

    /**
     * 예약 승인 대기중
     */
    String RESERVATION_STATUS_REQ = "STATUS_REQ";

    /**
     * 예약 승인 취소
     */
    String RESERVATION_STATUS_CANCEL = "STATUS_CANCEL";

    /**
     * 예약 승인
     */
    String RESERVATION_STATUS_APPROVED = "STATUS_APPROVED";
}
