package com.zerobase.srs.review.dto;

import com.zerobase.srs.review.entity.Review;
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
public class ReviewDto {

    Long id;

    private String userId;

    long storeId;
    String storeName;
    LocalDateTime reservationDt;

    String title;

    String contents;

    long score;

    long totalCount;
    long seq;

    public static ReviewDto of (Review review) {

        ReviewDto reviewDto = ReviewDto.builder()
                                            .id(review.getId())
                                            .userId(review.getUserId())
                                            .storeId(review.getStoreId())
                                            .storeName(review.getStoreName())
                                            .reservationDt(review.getReservationDt())
                                            .title(review.getTitle())
                                            .contents(review.getContents())
                                            .score(review.getScore())
                                            .build();

        return reviewDto;
    }

    public static List<ReviewDto> of (List<Review> reviews) {

        if (reviews == null) {
            return null;
        }

        List<ReviewDto> reviewList = new ArrayList<>();
        for (Review x : reviews) {
            reviewList.add(ReviewDto.of(x));
        }
        return reviewList;
    }
}
