package center.cyq.software.controller;

import center.cyq.software.entity.Game;
import center.cyq.software.entity.Review;
import center.cyq.software.entity.User;
import center.cyq.software.service.MyGameService;
import center.cyq.software.service.ReviewService;
import center.cyq.software.service.UserService;
import center.cyq.software.utils.MD5Utils;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@CrossOrigin(allowCredentials="true")
@Controller
public class UserController {
    @Value("${spring.mail.username}")
    private String from;
    private JavaMailSender mailSender;
    private DateFormat dateFormat;

    private UserService userService;
    private MyGameService myGameService;
    private ReviewService reviewService;

    @Autowired
    public UserController(JavaMailSender mailSender, UserService userService, MyGameService myGameService, ReviewService reviewService) {
        this.mailSender = mailSender;
        this.userService = userService;
        this.myGameService = myGameService;
        this.reviewService = reviewService;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    }

    @GetMapping("/register")
    @ResponseBody
    public JSONObject addUser(HttpServletRequest request) {
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
    public JSONObject sendCode(HttpServletRequest request, HttpSession session) {
        String mail = request.getParameter("registerMail");
//        System.out.println(mail);
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setFrom(from);
        // 设置抄送人，不加可能会被当成垃圾邮件
        mailMessage.setCc(from);
        mailMessage.setTo(mail);
        mailMessage.setSubject("游戏管理系统：验证码");
        String code = getRandomCode();
        mailMessage.setText("你的验证码为：" + code);
        session.setAttribute("code", code);
        JSONObject result = new JSONObject();
        try {
            mailSender.send(mailMessage);
            result.put("code", 200);
        }catch (Exception e){
//            System.out.println(e);
            result.put("code", 400);
        }
        return result;
    }

    @GetMapping("/register/checkCode")
    @ResponseBody
    public JSONObject checkCode(HttpServletRequest request, HttpSession session) {
        String code = (String) session.getAttribute("code");

        String mail = request.getParameter("registerMail");
        String verifyCode = request.getParameter("registerVerifyCode");

        JSONObject result = new JSONObject();
        if (code.equals(verifyCode)) {
            result.put("code", 200);
        } else {
            result.put("code", 400);
        }
        // 先不进行验证，使用服务器访问会有问题
//        result.put("code", 200);
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
        } else {
            result.put("type", 0);
            result.put("code", 200);
            List<JSONObject> obj = new ArrayList<>();
            obj.add(new JSONObject()
                    .element("userId", user.getUserId())
                    .element("userName", user.getUserName())
                    .element("gender", user.getGender())
                    .element("mail", user.getMail()));
            result.element("loginObj", obj);
        }
        return result;
    }

    @GetMapping("/resetPassword")
    @ResponseBody
    public JSONObject resetPassword(HttpServletRequest request) {
        String mail = request.getParameter("resetMail");
        String password = MD5Utils.code(request.getParameter("resetPassword"));
//        User user = userService.findUserByMail(new User(mail));

        JSONObject result = new JSONObject();
//        if (!user.getPassword().equals(password)){
//            result.put("code", 400);
//            return result;
//        }

        if (userService.updateUser(new User(mail, password)) == 1) {
            result.put("code", 200);
        } else {
            result.put("code", 400);
        }
        return result;
    }

    // 返回用户购物车内容
    @GetMapping("/myCart")
    @ResponseBody
    public JSONObject getCartInfo(HttpServletRequest request) {
        JSONObject result = new JSONObject();

        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        // 获取参数
        Integer userId = Integer.valueOf(request.getParameter("userId"));

        List<Game> gameList = myGameService.getCartInfo(userId);
        if (gameList == null) {
            result.put("code", 400);
            return result;
        }

        List<JSONObject> games = new ArrayList<>();
        float totalPrice = 0;
        for (Game g : gameList) {
            // 字段如果为null，则不会存入json，返回的数据中没有该字段
            JSONObject game = new JSONObject()
                    .element("gameId", g.getId())
                    .element("gameName", g.getName())
                    .element("price", g.getPrice())
                    .element("discount", g.getDiscount())
                    .element("picUrl", g.getPicUrl())
                    .element("publishTime", dateFormat.format(g.getPublishTime()));
            totalPrice += g.getPrice();
            games.add(game);
        }

        result.put("totalAmount", totalPrice);
        result.put("games", games);
        result.put("code", 200);
        return result;
    }

    @GetMapping("/deleteGame")
    @ResponseBody
    public JSONObject deleteGameInCart(HttpServletRequest request) {
        JSONObject result = new JSONObject();

        // 获取参数
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer gameId = Integer.valueOf(request.getParameter("gameId"));

        if (myGameService.deleteGameInCart(userId, gameId) != 1) {
            result.put("code", 400);
        } else {
            result.put("code", 200);
        }
        return result;
    }

    @GetMapping("/deleteAllGame")
    @ResponseBody
    public JSONObject deleteAllGameInCart(HttpServletRequest request) {
        JSONObject result = new JSONObject();

        // 获取参数
        Integer userId = Integer.valueOf(request.getParameter("userId"));

        if (myGameService.deleteAllGameInCart(userId) > 0) {
            result.put("code", 200);
        } else {
            result.put("code", 400);
        }
        return result;
    }

    @GetMapping("/addList")
    @ResponseBody
    public JSONObject addList(HttpServletRequest request) {
        JSONObject result = new JSONObject();

        // 获取参数
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer gameId = Integer.valueOf(request.getParameter("gameId"));

        // 操作成功码的判断
        Integer code = myGameService.addList(userId, gameId);
        if (code >= 0) {
            result.put("code", 200);
        } else {
            result.put("code", 400);
        }
        return result;
    }

    @GetMapping("/search")
    @ResponseBody
    public JSONObject searchGame(HttpServletRequest request) {
        JSONObject result = new JSONObject();
        // 获取参数
        String content = request.getParameter("content");

        // 操作成功码的判断
        List<Game> gamesList = myGameService.searchGame(content);
//        System.out.println(gamesList);
        if (gamesList == null) {
            result.put("code", 400);
            result.put("searchList", null);
            result.put("totalSearch", 0);
            return result;
        }
        // 时间格式化
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        List<JSONObject> gamesInfo = new ArrayList<>();
        for (Game g1 : gamesList) {
            // 字段如果为null，则不会存入json，返回的数据中没有该字段
            JSONObject game = new JSONObject()
                    .element("gameId", g1.getId())
                    .element("gameName", g1.getName())
                    .element("price", g1.getPrice())
                    .element("discount", g1.getDiscount())
                    .element("picUrl", g1.getPicUrl())
                    .element("publishTime", dateFormat.format(g1.getPublishTime()));
            gamesInfo.add(game);
        }
        result.put("code", 200);
        result.put("searchList", gamesInfo);
        result.put("totalSearch", gamesList.size());
        return result;
    }

    @GetMapping("/addComment")
    @ResponseBody
    public JSONObject addComment(HttpServletRequest request) {
        JSONObject result = new JSONObject();

        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");        // 获取参数
        Integer userId = Integer.valueOf(request.getParameter("userId"));
        Integer gameId = Integer.valueOf(request.getParameter("gameId"));
        String content = request.getParameter("content");
        Float rate = Float.valueOf(request.getParameter("rate"));

        try {
            Date time = dateFormat.parse(request.getParameter("time"));
            if (reviewService.addComment(new Review(userId, gameId, content, rate, time)) != 1) {
                result.put("code", 400);
                return result;
            }
        }catch (ParseException e){
            result.put("message", "时间格式不对，应为yyyy-MM-dd HH:mm:ss");
            return result;
        }
        result.put("code", 200);
        return result;
    }
}
