package center.cyq.software.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

@Controller
public class HelloController {

    @GetMapping("/")
    @ResponseBody
    public String index(HttpServletRequest request){
        return "hello, world";
    }

}
