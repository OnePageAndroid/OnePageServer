package kr.nexters.onepage.api.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@ControllerAdvice
public class ApiExceptionHandler {
	@ExceptionHandler({ Exception.class })
	public String handleException(HttpServletRequest request, Exception exception) {
		return "error";
	}
}
