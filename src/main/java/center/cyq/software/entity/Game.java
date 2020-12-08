package center.cyq.software.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Game {
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
    // 一个游戏对应多个评论
    private List<Review> reviews;
}
