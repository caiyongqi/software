package center.cyq.software.service;

import center.cyq.software.entity.Game;

import java.util.List;

public interface GameService {
    List<Game> getDiscountedGames(Integer nums);

    List<Game> getRecommendGames(Integer nums);

    List<Game> getLatestGames(Integer nums);

    Game getGameInfo(Integer id);
}
