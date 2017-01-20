package kr.nexters.onepage.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.convert.threeten.Jsr310JpaConverters;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import kr.nexters.onepage.api.OnePageApis;
import kr.nexters.onepage.domain.Domains;

@Import({
	CloudinaryConfig.class,
	MvcConfig.class,
	SwaggerConfig.class
})
@EntityScan(
	basePackageClasses = { Jsr310JpaConverters.class, Domains.class })
@ComponentScan(basePackageClasses = { OnePageApis.class, Domains.class })
@EnableJpaAuditing
@EnableJpaRepositories(basePackageClasses = { Domains.class })
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
