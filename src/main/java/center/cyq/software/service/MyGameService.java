package center.cyq.software.service;

import center.cyq.software.entity.Game;

import java.util.List;

public interface MyGameService {
    List<Game> getCartInfo(Integer userId);
}
