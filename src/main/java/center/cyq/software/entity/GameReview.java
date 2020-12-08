package center.cyq.software.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

/**
 * 好评度高的游戏
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class GameReview {
    private Integer id;
    private String name;
    private Date publishTime;
    private String publisher;
    private String picUrl;
    private String labels;
    private Float price;
    private String description;
    private Float discount;
    private String mvUrl;
    private String reviewContent;
    private Integer sale;
    private Integer gameId;
    private Double rate;
}
