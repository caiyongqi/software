package center.cyq.software.controller;

import center.cyq.software.entity.Game;
import center.cyq.software.entity.Review;
import center.cyq.software.entity.User;
import center.cyq.software.service.GameService;
import center.cyq.software.service.ReviewService;
import center.cyq.software.service.UserService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@CrossOrigin(allowCredentials="true")
@Controller
public class GameController {
    private DateFormat dateFormat;
    private GameService gameService;
    private ReviewService reviewService;
    private UserService userService;

    @Autowired
    public GameController(GameService gameService, ReviewService reviewService, UserService userService) {
        this.reviewService = reviewService;
        this.userService = userService;
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        this.gameService = gameService;
    }

    private List<JSONObject> getGames(List<Game> gameList){
        List<JSONObject> games = new ArrayList<>();
        for (Game g:gameList) {
            // 字段如果为null，则不会存入json，返回的数据中没有该字段
            JSONObject game = new JSONObject()
                    .element("gameId", g.getId())
                    .element("gameName", g.getName())
                    .element("price", g.getPrice())
                    .element("discount", g.getDiscount())
                    .element("picUrl", g.getPicUrl());
            games.add(game);
        }

        return games;
    }

    @GetMapping("/shoppingMall/discount")
    @ResponseBody
    public JSONObject getDiscountedGames(){
        JSONObject result = new JSONObject();
        List<Game> games = gameService.getDiscountedGames(4);
        if (games == null){
            result.put("code", 400);
            return result;
        }
        List<JSONObject> discountedGames = getGames(games);
        result.put("discount", discountedGames);
        result.put("code", 200);

        return result;
    }

    @GetMapping("/shoppingMall/recommend")
    @ResponseBody
    public JSONObject getRecommendGames(){
        JSONObject result = new JSONObject();
        List<Game> games = gameService.getRecommendGames(4);
        if (games == null){
            result.put("code", 400);
            return result;
        }
        List<JSONObject> recommendGames = getGames(games);
        result.put("recommend", recommendGames);
        result.put("code", 200);

        return result;
    }

    @GetMapping("/shoppingMall/lastest")
    @ResponseBody
    public JSONObject getLatestGames(){
        JSONObject result = new JSONObject();
        List<Game> games = gameService.getLatestGames(4);
        if (games == null){
            result.put("code", 400);
            return result;
        }
        List<JSONObject> latestGames = getGames(games);
        result.put("lastest", latestGames);
        result.put("code", 200);

        return result;
    }

    @GetMapping("/game")
    @ResponseBody
    public JSONObject getGameInfo(HttpServletRequest request){
        this.dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        Integer gameId = Integer.valueOf(request.getParameter("id"));
        JSONObject result = new JSONObject();
        Game game = gameService.getGameInfo(gameId);

        if (game == null){
            result.put("code", 400);
            return result;
        }

        List<JSONObject> reviewInfo = new ArrayList<>();
        List<Review> reviewList = reviewService.getReviews(game.getId());
        for (Review r:reviewList) {
            User user = userService.findUserById(r.getUserId());
            reviewInfo.add(new JSONObject()
                                .element("id", r.getId())
                                .element("gameId", gameId)
                                .element("content", r.getContent())
                                .element("userId", r.getUserId())
                                .element("author", user.getUserName())
                                .element("avatar", user.getPicUrl() == null ? user.getPicUrl() : "https://zos.alipayobjects.com/rmsportal/ODTLcjxAfvqbxHnVXCYX.png")
                                .element("datetime", dateFormat.format(r.getTime())));
        }
        result.put("data", new JSONObject()
                .element("gameId", game.getId())
                .element("gameName", game.getName())
                .element("publishTime", new SimpleDateFormat("yyyy-MM-dd").format(game.getPublishTime()))
                .element("publisher", game.getPublisher())
                .element("picUrl", game.getPicUrl())
                .element("labels", game.getLabels())
                .element("price", game.getPrice())
                .element("description", game.getDescription())
                .element("discount", game.getDiscount())
                .element("rate", reviewService.getRate(gameId) == null ? "暂无评分" : reviewService.getRate(gameId))
//                .element("reviewContent", game.getReviewContent())
                .element("mvUrl", game.getMvUrl())
                .element("reviewNum", reviewService.getReviewNum(game.getId()))
                .element("reviews", reviewInfo));

        result.put("code", 200);
        return result;
    }
}
