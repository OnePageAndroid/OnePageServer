package kr.nexters.onepage.domain.user;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class UserDto {
	private String email;

	public static UserDto of(User user) {
		return UserDto.builder()
			.email(user.getEmail())
			.build();
	}

	public static UserDto empty() {
		return UserDto.builder()
			.build();
	}

	public static UserDto err(String err){
		return UserDto.builder()
				.email(err)
				.build();
	}
}
