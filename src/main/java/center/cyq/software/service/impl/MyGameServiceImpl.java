package center.cyq.software.service.impl;

import center.cyq.software.dao.MyGameDao;
import center.cyq.software.entity.Game;
import center.cyq.software.service.MyGameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MyGameServiceImpl implements MyGameService {
    private MyGameDao myGameDao;

    @Autowired
    public MyGameServiceImpl(MyGameDao myGameDao) {
        this.myGameDao = myGameDao;
    }

    @Override
    public List<Game> getCartInfo(Integer userId) {
        return myGameDao.getCartInfo(userId);
    }

    @Override
    public Integer deleteGameInCart(Integer userId, Integer gameId) {
        return myGameDao.deleteGameInCart(userId, gameId);
    }

    @Override
    public Integer deleteAllGameInCart(Integer userId) {
        return myGameDao.deleteAllGameInCart(userId);
    }

    @Override
    public Integer addList(Integer userId, Integer gameId) {
        // 如果不存在则继续添加
        return myGameDao.addList(userId, gameId);
    }

    @Override
    public List<Game> searchGame(String content) {
        return myGameDao.searchGame(content);
    }

    @Override
    public List<Game> classifyHot() {
        return myGameDao.classifyHot();
    }
}
