package center.cyq.software.service;

import center.cyq.software.entity.Game;
import center.cyq.software.entity.GameReview;

import java.util.List;

public interface MyGameService {
    List<Game> getCartInfo(Integer userId);
    Integer deleteGameInCart(Integer userId, Integer gameId);
    Integer deleteAllGameInCart(Integer userId);
    Integer addList(Integer userId, Integer gameId);
    List<Game> searchGame(String content);
    List<Game> classifyHot();
    List<GameReview> classifyGood();
    List<Game> myGame(Integer userId);
    Integer buyGame(Integer userId);
    List<Game> classifyWill();
}
