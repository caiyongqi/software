package center.cyq.software.dao;

import center.cyq.software.entity.Game;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MyGameDao {
    // 返回用户购物车中的游戏信息
    List<Game> getCartInfo(Integer userId);
}
