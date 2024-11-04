package com.zerobase.srs.review.sevice;

import com.zerobase.srs.review.dto.ReviewDto;
import com.zerobase.srs.review.model.ReviewInput;
import com.zerobase.srs.review.model.ReviewParam;

import java.util.List;

public interface ReviewService {

    /**
     * 내 리뷰 목록
     */
    List<ReviewDto> list(ReviewParam parameter);

    /**
     * 리뷰 등록
     */
    boolean add(ReviewInput parameter);

    /**
     * 관리자 리뷰 목록
     */
    List<ReviewDto> listAll(ReviewParam parameter);

    /**
     * 리뷰 삭제 (관리자, 작성자)
     */
    boolean delete(long id);

    /**
     * 파트너 본인 상점 리뷰 목록
     */
    List<ReviewDto> partnerList(List<Long> storeIds);

    /**
     * 리뷰 수정
     */
    boolean update(ReviewInput parameter);
}
