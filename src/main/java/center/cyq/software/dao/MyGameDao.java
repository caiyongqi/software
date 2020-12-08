package center.cyq.software.dao;

import center.cyq.software.entity.Game;
import center.cyq.software.entity.GameReview;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MyGameDao {
    // 返回用户购物车中的游戏信息
    List<Game> getCartInfo(Integer userId);

    // 删除购物车中的游戏
    // Dao默认只能传递一个参数，多个需要添加@Param注解
    Integer deleteGameInCart(@Param("userId") Integer userId, @Param("gameId") Integer gameId);

    // 删除购物车所有商品
    Integer deleteAllGameInCart(Integer userId);

    // 添加游戏到购物车
    Integer addList(@Param("userId") Integer userId, @Param("gameId") Integer gameId);

    // 返回查询的游戏信息
    List<Game> searchGame(@Param("content") String content);

    // 获取热销商品排行
    List<Game> classifyHot();

    //  获取卖的好的商品
    List<GameReview> classifyGood();

    // 获取已经购买的游戏
    List<Game> myGame(@Param("userId") Integer userId);

    // 购买游戏
    Integer buyGame(@Param("userId") Integer userId);

    // 返回即将发行的游戏
    List<Game> classifyWill();
}
