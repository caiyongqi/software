package center.cyq.software.dao;

import center.cyq.software.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface UserDao {
    // 注册(添加)用户
    Integer addUser(User user);

    User findUserByMail(User user);

    Integer updateUser(User user);
}
