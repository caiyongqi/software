package center.cyq.software.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private Integer userId;
    private String userName;
    private Integer gender;
    private String mail;
    private String password;

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
