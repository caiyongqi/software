package center.cyq.software.service;

import center.cyq.software.entity.Review;

import java.util.List;

public interface ReviewService {
    List<Review> getReviews(Integer gameId);
    int getReviewNum(Integer gameId);
    Float getRate(Integer gameId);
    int addComment(Review review);
}
