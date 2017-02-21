package kr.nexters.onepage.api;

import kr.nexters.onepage.api.common.ResponseDto;
import kr.nexters.onepage.domain.location.LocationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MigrationController {
	@Autowired
	private LocationService locationService;

	@RequestMapping("/migration/location")
	public ResponseDto migrationLocation() {
		locationService.translateMigration();
		return ResponseDto.ofSuccess("success");
	}
}
