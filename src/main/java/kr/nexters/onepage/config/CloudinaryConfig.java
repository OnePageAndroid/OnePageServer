package kr.nexters.onepage.config;

import com.cloudinary.Cloudinary;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;

@Slf4j
@Configuration
public class CloudinaryConfig {
	@Value("${cloudinary.name}")
	private String cloudName;
	@Value("${cloudinary.key}")
	private String cloudKey;
	@Value("${cloudinary.secret}")
	private String cloudSecret;

	@Bean
	public Cloudinary cloudinary(){
		return new Cloudinary(new HashMap(){{
			put("cloud_name", cloudName);
			put("api_key", cloudKey);
			put("api_secret", cloudSecret);
		}});
	}
}
