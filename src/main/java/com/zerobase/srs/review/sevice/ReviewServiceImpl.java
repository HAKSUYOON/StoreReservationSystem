package com.zerobase.srs.review.sevice;

import com.zerobase.srs.review.dto.ReviewDto;
import com.zerobase.srs.review.entity.Review;
import com.zerobase.srs.review.model.ReviewInput;
import com.zerobase.srs.review.model.ReviewParam;
import com.zerobase.srs.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class ReviewServiceImpl implements ReviewService {

    private final ReviewRepository reviewRepository;

    public LocalDateTime getLocalDateTime(String value) {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        try{
            return LocalDateTime.parse(value, formatter);
        } catch (Exception e){

        }
        return null;
    }

    @Override
    public List<ReviewDto> list(ReviewParam parameter) {

        Optional<List<Review>> optionalReviews = reviewRepository.findByUserId(parameter.getUserId());
        if (optionalReviews.isEmpty()) {
            return null;
        }

        return ReviewDto.of(optionalReviews.get());
    }

    @Override
    public boolean add(ReviewInput parameter) {

        LocalDateTime reservationDt = getLocalDateTime(parameter.getReservationDt());

        Optional<Review> optionalReview = reviewRepository.findByUserIdAndStoreIdAndReservationDt(parameter.getUserId(), parameter.getStoreId(), reservationDt);
        if (optionalReview.isPresent()) {
            return false;
        }

        Review review = Review.builder()
                .userId(parameter.getUserId())
                .storeId(parameter.getStoreId())
                .storeName(parameter.getStoreName())
                .reservationDt(reservationDt)
                .title(parameter.getTitle())
                .contents(parameter.getContents())
                .score(parameter.getScore())
                .build();
        reviewRepository.save(review);

        return true;
    }

    @Override
    public List<ReviewDto> listAll(ReviewParam parameter) {

        List<Review> list = reviewRepository.findAll();

        return ReviewDto.of(list);
    }

    @Override
    public boolean delete(long id) {

        reviewRepository.deleteById(id);

        return true;
    }

    @Override
    public List<ReviewDto> partnerList(List<Long> storeIds) {

        for (Long storeId : storeIds) {
            Optional<List<Review>> optionalReview = reviewRepository.findByStoreId(storeId);
            return ReviewDto.of(optionalReview.get());
        }

        return null;
    }

    @Override
    public boolean update(ReviewInput parameter) {

        Optional<Review> optionalReview = reviewRepository.findById(parameter.getId());
        if (optionalReview.isEmpty()) {
            return false;
        }

        Review review = optionalReview.get();

        review.setTitle(parameter.getTitle());
        review.setContents(parameter.getContents());
        review.setScore(parameter.getScore());

        reviewRepository.save(review);
        return true;
    }
}
