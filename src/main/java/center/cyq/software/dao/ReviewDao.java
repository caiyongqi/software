package center.cyq.software.dao;

import center.cyq.software.entity.Review;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
 public interface ReviewDao {
    // 返回游戏的评论信息
    List<Review> getReviews(Integer gameId);
    // 返回游戏评论数
    int getReviewNum(Integer gameId);
    // 返回游戏平均评分
    float getRate(Integer gameId);
}
