package center.cyq.software.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private Integer gender;
    private String mail;
    private String password;
    private String picUrl;
    // 一个用户有多个游戏
//    private List<Game> games;

    public User(String userName, String password, Integer gender, String mail) {
        this.userName = userName;
        this.password = password;
        this.gender = gender;
        this.mail = mail;
    }

    public User(String mail) {
        this.mail = mail;
    }

    public User(String mail, String password) {
        this.mail = mail;
        this.password = password;
    }
}
