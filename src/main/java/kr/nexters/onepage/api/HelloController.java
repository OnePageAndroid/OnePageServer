package kr.nexters.onepage.api;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@Api(value = "test API", description = "test API", basePath = "/api/v1/hello")
@RestController
@RequestMapping("/api/v1/hello")
public class HelloController {
    @ApiOperation(value = "hello api", notes = "hello api")
    @RequestMapping(value = "", method = RequestMethod.GET)
    public String index(){
        return "hello";
    }

    @ApiOperation(value = "exception api", notes = "exception api")
    @RequestMapping(value = "/exception", method = RequestMethod.GET)
    public void exception() {
        throw new RuntimeException("test");
    }
}