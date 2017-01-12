package kr.nexters.onepage.domain.location;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class LocationService {

	@Autowired
	private LocationRepository locationRepository;

	public void saveLocation() {

	}
}
