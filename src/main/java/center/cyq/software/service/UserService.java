package center.cyq.software.service;

import center.cyq.software.entity.User;

public interface UserService {
    Integer addUser(User user);

    User findUserByMail(User user);

    Integer updateUser(User user);

    User findUserById(Integer userId);

    Integer updateUserById(User user);
}
