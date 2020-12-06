package center.cyq.software.service.impl;

import center.cyq.software.dao.GameDao;
import center.cyq.software.entity.Game;
import center.cyq.software.service.GameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GameServiceImpl implements GameService {
    private GameDao gameDao;

    @Autowired
    public GameServiceImpl(GameDao gameDao) {
        this.gameDao = gameDao;
    }

    @Override
    public List<Game> getDiscountedGames(Integer nums) {
        return gameDao.getDiscountedGames(nums);
    }

    @Override
    public List<Game> getRecommendGames(Integer nums){
        return gameDao.getRecommendGames(nums);
    }

    @Override
    public List<Game> getLatestGames(Integer nums) {
        return gameDao.getLatestGames(nums);
    }

    @Override
    public Game getGameInfo(Integer id) {
        return gameDao.getGameInfo(id);
    }
}
