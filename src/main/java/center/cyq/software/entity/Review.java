package center.cyq.software.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Review {
    private Integer id;
    private Integer gameId;
    private String content;
    private Integer userId;
    private Date time;
    private Integer type;

    // 发表该评论的用户
    private User user;
}
