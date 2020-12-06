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
}
