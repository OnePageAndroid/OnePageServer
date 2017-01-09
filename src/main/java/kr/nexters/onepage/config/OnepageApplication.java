package kr.nexters.onepage.config;

import kr.nexters.onepage.api.OnePageApis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

@ComponentScan(basePackageClasses = { OnePageApis.class })
@SpringBootApplication
public class OnepageApplication {

	public static void main(String[] args) {
		SpringApplication.run(OnepageApplication.class, args);
	}

	@Bean(initMethod = "init")
	public InitDataLoader initDataLoader() {
		return new InitDataLoader();
	}
}
