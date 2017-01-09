package kr.nexters;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by iljun on 2017-01-09.
 */
@RestController
public class HomeController {
    @RequestMapping("/")
    public String index(){
        return "onePage";
    }
}
