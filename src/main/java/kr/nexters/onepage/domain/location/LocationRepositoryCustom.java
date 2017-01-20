package kr.nexters.onepage.domain.location;

import java.util.List;

public interface LocationRepositoryCustom {
	List<Location> rangeByLatLng(Double latitude, Double longitude);
}
