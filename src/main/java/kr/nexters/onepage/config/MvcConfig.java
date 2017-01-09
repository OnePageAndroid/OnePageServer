package kr.nexters.onepage.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//@Slf4j
@Configuration
public class MvcConfig extends WebMvcConfigurerAdapter {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // resourceHandler 서버 url, resourceLocations 현재 파일 위치
        registry.addResourceHandler("/resources/**").addResourceLocations("/resources/").setCachePeriod(10000);
    }

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
