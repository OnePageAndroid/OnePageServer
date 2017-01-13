package kr.nexters.onepage.api;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    @RequestMapping("")
    public String index(){
        return "hello";
    }

    @RequestMapping("/exception")
    public void exception() {
        throw new RuntimeException("test");
    }
}