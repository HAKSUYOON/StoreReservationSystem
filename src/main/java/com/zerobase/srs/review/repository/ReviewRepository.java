package com.zerobase.srs.review.repository;

import com.zerobase.srs.review.entity.Review;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    Optional<List<Review>> findByUserId(String userId);

    Optional<Review> findByUserIdAndStoreIdAndReservationDt(String userId, long storeId, LocalDateTime reservationDt);

    Optional<List<Review>> findByStoreId(Long storeId);
}
