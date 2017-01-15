package kr.nexters.onepage.domain.common;

public class OnePageServiceException extends RuntimeException {
	public OnePageServiceException(String message) {
		super(message);
	}
	public OnePageServiceException(String message, Exception e) {
		super(message, e);
	}
	public OnePageServiceException(Exception e) {
		super(e.getMessage(), e);
	}
}
