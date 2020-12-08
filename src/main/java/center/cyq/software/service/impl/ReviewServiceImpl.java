package center.cyq.software.service.impl;

import center.cyq.software.dao.ReviewDao;
import center.cyq.software.entity.Review;
import center.cyq.software.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {
    private ReviewDao reviewDao;

    @Autowired
    public ReviewServiceImpl(ReviewDao reviewDao) {
        this.reviewDao = reviewDao;
    }

    @Override
    public List<Review> getReviews(Integer gameId) {
        return reviewDao.getReviews(gameId);
    }

    @Override
    public int getReviewNum(Integer gameId) {
        return reviewDao.getReviewNum(gameId);
    }

    @Override
    public int getPosReviewNum(Integer gameId) {
        return reviewDao.getPosReviewNum(gameId);
    }
}
