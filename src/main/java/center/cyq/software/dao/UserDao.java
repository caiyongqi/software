package center.cyq.software.dao;

import center.cyq.software.entity.Game;
import center.cyq.software.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface UserDao {
    // 注册(添加)用户
    Integer addUser(User user);

    User findUserByMail(User user);

    Integer updateUser(User user);

    User findUserById(Integer userId);

    // 返回用户购物车中的游戏信息
//    List<Game> getCartInfo(Integer userId);
}
