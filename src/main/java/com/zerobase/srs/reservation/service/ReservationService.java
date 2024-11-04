package com.zerobase.srs.reservation.service;

import com.zerobase.srs.reservation.dto.ReservationDto;
import com.zerobase.srs.reservation.model.ReservationInput;
import com.zerobase.srs.reservation.model.ReservationParam;

import java.util.List;

public interface ReservationService {
    /**
     * 내 예약목록
     */
    List<ReservationDto> list(ReservationParam parameter);

    /**
     * 상점 예약
     */
    boolean add(ReservationInput parameter);

    /**
     * 파트너 예약목록
     */
    List<ReservationDto> partnerList(ReservationParam parameter);

    /**
     * 예약 승인
     */
    boolean approved(long id);

    /**
     * 예약 취소
     */
    boolean canceled(long id);

    /**
     * 상점 이용 완료
     */
    boolean use(long id);
}
