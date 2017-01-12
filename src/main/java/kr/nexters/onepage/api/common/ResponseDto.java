package kr.nexters.onepage.api.common;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseDto {
	private Status status;
	private String message;

	enum Status {
		SUCCESS,
		FAIL
	}
}