package center.cyq.software.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MyGame {
    private Integer userId;
    private Integer gameId;

    // 一个用户对应多个游戏
    private User user;
    private List<Game> games;
}
