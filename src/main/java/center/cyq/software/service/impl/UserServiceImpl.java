package center.cyq.software.service.impl;

import center.cyq.software.dao.UserDao;
import center.cyq.software.entity.User;
import center.cyq.software.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {
    private UserDao userDao;

    @Autowired
    public UserServiceImpl(UserDao userDao) {
        this.userDao = userDao;
    }

    @Override
    public Integer addUser(User user) {
        return userDao.addUser(user);
    }

    @Override
    public User findUserByMail(User user) {
        return userDao.findUserByMail(user);
    }

    @Override
    public Integer updateUser(User user) {
        return userDao.updateUser(user);
    }

    @Override
    public User findUserById(Integer userId) {
        return userDao.findUserById(userId);
    }
}
