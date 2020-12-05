package center.cyq.software.controller;

import center.cyq.software.entity.User;
import center.cyq.software.service.UserService;
import center.cyq.software.utils.MD5Utils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Random;

@Controller
public class UserController {
    @Value("${spring.mail.username}")
    private String from;
    private JavaMailSender mailSender;

    private UserService userService;

    public UserController(JavaMailSender mailSender, UserService userService) {
        this.mailSender = mailSender;
        this.userService = userService;
    }

    @GetMapping("/register")
    @ResponseBody
    public JSONObject addUser(HttpServletRequest request){
        String userName = request.getParameter("userName");
        Integer gender = Integer.valueOf(request.getParameter("gender"));
        String password = MD5Utils.code(request.getParameter("registerPassword"));
        String mail = request.getParameter("registerMail");
        User user = new User(userName, password, gender, mail);

        JSONObject result = new JSONObject();

        if (userService.addUser(user) == 1) {
            result.put("code", 200);
        } else {
            result.put("code", 400);
        }
        return result;
    }

    private static String getRandomCode() {
        StringBuilder str = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < 6; i++) {
            str.append(random.nextInt(10));
        }
        return str.toString();
    }

    @GetMapping("/register/sendCode")
    @ResponseBody
    public JSONObject sendCode(HttpServletRequest request){
        String mail = request.getParameter("registerMail");

        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        mailMessage.setTo(mail);
        mailMessage.setSubject("游戏管理系统：验证码");
        String code = getRandomCode();
        mailMessage.setText("你的验证码为：" + code);
        request.getSession().setAttribute("code", code);
        JSONObject result = new JSONObject();
        try {
            mailSender.send(mailMessage);
            result.put("code", 200);
        }catch (Exception e){
            result.put("code", 400);
        }

        return result;
    }

    @GetMapping("/register/checkCode")
    @ResponseBody
    public JSONObject checkCode(HttpServletRequest request, HttpSession session){
        String code = (String) session.getAttribute("code");

        String mail = request.getParameter("registerMail");
        String verifyCode = request.getParameter("registerVerifyCode");

        JSONObject result = new JSONObject();
        if (code.equals(verifyCode)){
            result.put("code", 200);
        }else{
            result.put("code", 400);
        }

        return result;
    }

    @GetMapping("/login")
    @ResponseBody
    public JSONObject login(HttpServletRequest request) {
        // 获取参数
        String mail = request.getParameter("loginMail");
        String password = MD5Utils.code(request.getParameter("loginPassword"));

        // 查询
        User user = userService.findUserByMail(new User(mail));
        JSONObject result = new JSONObject();
        if (user == null) {
            result.put("type", 1);
            result.put("code", 400);
        } else if (!user.getPassword().equals(password)) {
            result.put("type", 0);
            result.put("code", 400);
        }else{
            result.put("type", 0);
            result.put("code", 200);
        }
        return result;
    }

    @GetMapping("/resetPassword")
    @ResponseBody
    public JSONObject resetPassword(HttpServletRequest request){
        String mail = request.getParameter("resetMail");
        String password = MD5Utils.code(request.getParameter("resetPassword"));
//        User user = userService.findUserByMail(new User(mail));

        JSONObject result = new JSONObject();
//        if (!user.getPassword().equals(password)){
//            result.put("code", 400);
//            return result;
//        }

        if(userService.updateUser(new User(mail, password)) == 1){
            result.put("code", 200);
        }else{
            result.put("code", 400);
        }
        return result;
    }

}
