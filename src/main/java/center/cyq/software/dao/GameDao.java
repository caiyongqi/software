package center.cyq.software.dao;

import center.cyq.software.entity.Game;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface GameDao {
    // 按折扣排序
    List<Game> getDiscountedGames(Integer nums);

    // 没有排序，不知道按什么推荐
    List<Game> getRecommendGames(Integer nums);

    // 按发布时间降序排序
    List<Game> getLatestGames(Integer nums);

    // 获取游戏具体信息
    Game getGameInfo(Integer id);
}
