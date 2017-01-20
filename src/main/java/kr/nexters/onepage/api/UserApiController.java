package kr.nexters.onepage.api;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Preconditions;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.user.UserDto;
import kr.nexters.onepage.domain.user.UserService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Api(value = "유저 API", description = "유저 API", basePath = "/api/v1/user")
@RestController
@RequestMapping("/api/v1/user")
public class UserApiController {
	@Autowired
	private UserService userService;

	@ApiOperation(value = "유저 저장", notes = "유저 저장")
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ResponseDto save(@RequestParam String email) {
		Preconditions.checkNotNull(email, "email Parameter가 존재하지 않음");
		Preconditions.checkState(Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim()),
				"이메일 형식이 아닙니다.");
		try {
			userService.saveUser(email);
			return ResponseDto.ofSuccess("유저 저장 성공");
		} catch (Exception e) {
			log.error("user save : " + e.getMessage(), e);
			return ResponseDto.ofFail("유저 저장 실패");
		}
	}

	@ApiOperation(value = "유저 조회", notes = "유저 조회")
	@RequestMapping(value = "", method = RequestMethod.GET)
	public UserDto findByEmail(@RequestParam String email) {
		Preconditions.checkNotNull(email,"email Parameter가 존재하지않음");
		Preconditions.checkState(Pattern.matches("[\\w\\~\\-\\.]+@[\\w\\~\\-]+(\\.[\\w\\~\\-]+)+", email.trim()),
				"이메일 형식이 아닙니다.");
		try {
			return userService.findDtoByEmail(email);
		} catch (Exception e) {
			log.error("user findByEmail : " + e.getMessage(), e);
			return UserDto.empty();
		}
	}
}
