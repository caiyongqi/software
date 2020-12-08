package center.cyq.software.controller;

import center.cyq.software.service.BannerService;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@CrossOrigin(allowCredentials="true")
@Controller
public class BannerController {
    private BannerService bannerService;

    @Autowired
    public BannerController(BannerService bannerService) {
        this.bannerService = bannerService;
    }

    @GetMapping("/shoppingMall/banner")
    @ResponseBody
    public JSONObject getBanners(){
        JSONObject result = new JSONObject();
        result.put("code", 200);
        result.put("banners", bannerService.getBanners(6));
        return result;
    }
}
