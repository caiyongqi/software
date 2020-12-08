package center.cyq.software.dao;

import center.cyq.software.entity.Game;
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
}
