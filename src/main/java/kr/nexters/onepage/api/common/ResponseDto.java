package kr.nexters.onepage.api.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ResponseDto {
	private Status status;
	private String message;

	enum Status {
		SUCCESS,
		FAIL
	}

	public static ResponseDto ofSuccess(String message) {
		return ResponseDto.builder()
			.status(Status.SUCCESS)
			.message(message).build();
	}

	public static ResponseDto ofFail(String message) {
		return ResponseDto.builder()
			.status(Status.FAIL)
			.message(message).build();
	}
}